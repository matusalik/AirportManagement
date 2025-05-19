package com.management.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.DTO.FlightDTO;
import com.management.Model.Airplane;
import com.management.Model.Airport;
import com.management.Model.Flight;
import com.management.Model.Passenger;
import com.management.Repositories.AirplaneRepository;
import com.management.Repositories.AirportRepository;
import com.management.Repositories.FlightRepository;
import com.management.Repositories.PassengerRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	AirplaneRepository airplaneRepository;
	
	@GetMapping("/findAllFlights")
	public @ResponseBody Iterable<FlightDTO>getFlights(){
		return StreamSupport.stream(flightRepository.findAll().spliterator(), false)
				.map(FlightDTO::new)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/findFlightById/{id}")
	public @ResponseBody ResponseEntity<Object> getFlightById(@PathVariable String id) {
		try {
			Integer fid = Integer.parseInt(id);
			Flight flight = flightRepository.findById(fid).orElse(null);
			if(flight == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Couldn't find flight with ID: " + fid);
			}
			else {
				return ResponseEntity.ok(new FlightDTO(flight));
			}
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	@PostMapping("/addPassengerToFlight")
	public ResponseEntity<String> addPassengerToFlight(@RequestParam String flightId, @RequestParam String passengerId){
		try {
			Integer fid = Integer.parseInt(flightId);
			Integer pid = Integer.parseInt(passengerId);
			Optional<Flight>optionalFlight = flightRepository.findById(fid);
			Optional<Passenger>optionalPassenger = passengerRepository.findById(pid);
			if(optionalFlight.isEmpty() || optionalPassenger.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight or Passenger not found.");
			}
			Flight flight = optionalFlight.get();
		    Passenger passenger = optionalPassenger.get();
		    if(!flight.getPassengerList().contains(passenger)) {
		    	flight.getPassengerList().add(passenger);
		    	passenger.getFlights().add(flight);
		    	flightRepository.save(flight);
		    	return ResponseEntity.ok("Passenger added to flight.");
		    }
		    return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Passenger is already assigned to this flight.");
			
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Format of either flightId or passengerId is invalid!");
		}
	}
	@DeleteMapping("/removePassengerFromFlight")
	public ResponseEntity<String>removePassengerFromFlight(@RequestParam String idFlight, @RequestParam String idPass){
		try {
			Integer fid = Integer.parseInt(idFlight);
			Integer pid = Integer.parseInt(idPass);
			Flight flight = flightRepository.findById(fid).orElse(null);
			if(flight == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found.");
			}
			Set<Passenger>passengers = flight.getPassengerList();
			for(Passenger p : passengers) {
				if(p.getIdPassenger() == pid) {
					passengers.remove(p);
					flight.setPassengerList(passengers);
					flightRepository.save(flight);
					return ResponseEntity.ok("Passenger succesfully removed from flight.");
				}
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger with id: " + pid + " wasn't found in flight with id: " + fid);		
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Format of either flightId or passengerId is invalid!");
		}
	}
	
	@DeleteMapping("/deleteFlightById/{id}")
	public ResponseEntity<String> deleteFlight(@PathVariable String id){
		try {
			Integer fid = Integer.parseInt(id);
			Flight flight = flightRepository.findById(fid).orElse(null);
			if(flight == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No flight found with ID: " + fid);
			}
			else {
				for(Passenger p : new HashSet<>(flight.getPassengerList())) {
					p.getFlights().remove(flight);
				}
				flight.getPassengerList().clear();
				flightRepository.delete(flight);
				return ResponseEntity.ok("Flight with ID " + fid + " has been deleted.");
			}
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	@PatchMapping("/updateFlight/{id}")
	public ResponseEntity<String>updateFlight(@PathVariable String id, @RequestBody FlightDTO dto){
		try {
			Integer fid = Integer.parseInt(id);
			Flight flight = flightRepository.findById(fid).orElse(null);
			if(flight == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No flight found with ID: " + fid);
			}
			if(dto.getDepartureDate() != null) {
				flight.setDepartureDate(dto.getDepartureDate());
			}
			if(dto.getArrivalDate() != null) {
				flight.setArrivalDate(dto.getArrivalDate());
			}
			if(dto.getCheckIn() != null) {
				flight.setCheckIn(dto.getCheckIn());
			}
			if(dto.getDepartureAirport() != null) {
				Airport depAirport = airportRepository.findById(dto.getDepartureAirport().getIdAirport()).orElse(null);
				if(depAirport == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Departure airport not found.");
				}
				flight.setDepartureAirport(depAirport);
			}
			if(dto.getArrivalAirport() != null) {
				Airport arrAirport = airportRepository.findById(dto.getArrivalAirport().getIdAirport()).orElse(null);
				if(arrAirport == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Arrival airport not found.");
				}
				flight.setArrivalAirport(arrAirport);
			}
			if(dto.getAirplane() != null) {
				Airplane airplane = airplaneRepository.findById(dto.getAirplane().getIdAirplane()).orElse(null);
				if(airplane == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Airplane not found.");
				}
				flight.setAirplane(airplane);
			}
			flightRepository.save(flight);
			return ResponseEntity.ok("Flight with ID " + fid + " updated successfully.");
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	@PutMapping("/putFlight/{id}")
	public ResponseEntity<String>putFlight(@PathVariable String id, @RequestBody FlightDTO dto){
		try {
			Integer fid = Integer.parseInt(id);
			Flight flight = flightRepository.findById(fid).orElse(null);
			if(flight == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No flight found with ID: " + fid);
			}
			if(!isDtoComplete(dto)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                .body("In PutMapping you have to pass full object!");
			}
			flight.setAirplane(dto.getAirplane());
			flight.setArrivalAirport(dto.getArrivalAirport());
			flight.setArrivalDate(dto.getArrivalDate());
			flight.setCheckIn(dto.getCheckIn());
			flight.setDepartureAirport(dto.getDepartureAirport());
			flight.setDepartureDate(dto.getDepartureDate());
			flightRepository.save(flight);
			return ResponseEntity.ok("Flight with ID " + fid + " updated successfully.");
			
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	private boolean isDtoComplete(FlightDTO dto) {
		return dto.getAirplane() != null &&
				dto.getArrivalAirport() != null &&
				dto.getArrivalDate() != null &&
				dto.getCheckIn() != null &&
				dto.getDepartureAirport() != null &&
				dto.getDepartureDate() != null;
	}
}
















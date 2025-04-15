package com.management.Controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.DTO.FlightDTO;
import com.management.Model.Flight;
import com.management.Model.Passenger;
import com.management.Repositories.FlightRepository;
import com.management.Repositories.PassengerRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
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
}
















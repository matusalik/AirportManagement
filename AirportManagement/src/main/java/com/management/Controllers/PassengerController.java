package com.management.Controllers;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.DTO.PassengerDTO;
import com.management.Model.Flight;
import com.management.Model.Passenger;
import com.management.Repositories.FlightRepository;
import com.management.Repositories.PassengerRepository;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	
	@GetMapping("/findPassengerById/{id}")
	public @ResponseBody ResponseEntity<Object>getPassenger(@PathVariable String id){
		try {
			Integer pid = Integer.parseInt(id);
			Passenger passenger = passengerRepository.findById(pid).orElse(null);
			if(passenger == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Couldn't find passenger with ID: " + pid);
			}
			return ResponseEntity.ok(passenger);
		}
		catch(NumberFormatException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	@GetMapping("/findAllPassengers")
	public @ResponseBody Iterable<PassengerDTO>getPassengers(){
		return StreamSupport.stream(passengerRepository.findAll().spliterator(), false)
                .map(PassengerDTO::new)
                .collect(Collectors.toList());
	}
	
	@DeleteMapping("/deletePassenger/{id}")
	public String deleteAirport(@PathVariable String id) {
		try {
			Integer pid = Integer.parseInt(id);
			Optional<Passenger>optionalPassenger = passengerRepository.findById(pid);
			if(optionalPassenger.isPresent()) {
				Passenger passenger = optionalPassenger.get();
				for(Flight flight : passenger.getFlights()) {
					flight.getPassengerList().remove(passenger);
				}
				flightRepository.saveAll(passenger.getFlights());
				passengerRepository.deleteById(pid);
				return "Passenger with ID " + pid + " has been deleted.";
			}
			else {
	            return "No passenger found with ID: " + pid;
	        }	
		}
		catch(NumberFormatException e) {
			return "Invalid ID format: '" + id + "'. Must be an integer.";
		}
	}
	
	@PostMapping("/addPassenger")
	public Passenger addPassenger(@RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
	}
}



















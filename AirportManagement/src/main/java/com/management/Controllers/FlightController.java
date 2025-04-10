package com.management.Controllers;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.DTO.FlightDTO;
import com.management.Model.Flight;
import com.management.Model.Passenger;
import com.management.Repositories.FlightRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	FlightRepository flightRepository;
	
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
















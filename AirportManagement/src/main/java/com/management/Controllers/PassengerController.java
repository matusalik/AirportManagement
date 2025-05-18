package com.management.Controllers;
import java.util.Optional;
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
	
	
	@PostMapping("/addPassenger")
	public Passenger addPassenger(@RequestBody Passenger passenger) {
		return passengerRepository.save(passenger);
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
	@PatchMapping("/updatePassenger/{id}")
	public ResponseEntity<String>updatePassenger(@PathVariable String id, @RequestBody PassengerDTO dto){
		try {
			Integer pid = Integer.parseInt(id);
			Passenger pass = passengerRepository.findById(pid).orElse(null);
			if(pass == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No airplane found with ID: " + pid);
			}
			if(dto.getPassportId() != null) {
				pass.setPassportId(dto.getPassportId());
			}
			if(dto.getName() != null) {
				pass.setName(dto.getName());
			}
			if(dto.getSurname() != null) {
				pass.setSurname(dto.getSurname());
			}
			if(dto.getBirthDate() != null) {
				pass.setBirthDate(dto.getBirthDate());
			}
			passengerRepository.save(pass);
			return ResponseEntity.ok("Passenger with ID " + pid + " updated successfully.");
				
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	@PutMapping("/putPassenger/{id}")
	public ResponseEntity<String>putPassenger(@PathVariable String id, @RequestBody PassengerDTO dto){
		try {
			Integer pid = Integer.parseInt(id);
			Passenger pass = passengerRepository.findById(pid).orElse(null);
			if(pass == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No passenger found with ID: " + pid);
			}
			if(!isDtoComplete(dto)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                .body("In PutMapping you have to pass full object!");
			}
			pass.setBirthDate(dto.getBirthDate());
			pass.setName(dto.getName());
			pass.setPassportId(dto.getPassportId());
			pass.setSurname(dto.getSurname());
			passengerRepository.save(pass);
			return ResponseEntity.ok("Passenger with ID " + pid + " updated successfully.");
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	private boolean isDtoComplete(PassengerDTO dto) {
		return dto.getBirthDate() != null &&
				dto.getName() != null &&
				dto.getPassportId() != null &&
				dto.getSurname() != null;
	}
}



















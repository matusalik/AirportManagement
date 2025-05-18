package com.management.Controllers;

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

import com.management.Model.Airplane;
import com.management.Repositories.AirplaneRepository;


@RestController
@RequestMapping("/airplanes")
public class AirplaneController {
	@Autowired 
	AirplaneRepository airplaneRepository;
	
	
	@GetMapping("/findAirplaneById/{id}")
	public @ResponseBody ResponseEntity<Object> getAirplane(@PathVariable String id) {
		try {
			Integer aid = Integer.parseInt(id);
			Airplane airplane = airplaneRepository.findById(aid).orElse(null);
			if(airplane == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Couldn't find airplane with ID: " + aid);
			}
			return ResponseEntity.ok(airplane);
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
		
	
	@GetMapping("/findAllAirplanes")
	public @ResponseBody Iterable<Airplane>getAirplanes(){
		return airplaneRepository.findAll();
	}
	
	
	@PostMapping("/addAirplane")
	public Airplane addAirplane(@RequestBody Airplane airplane) {
		return airplaneRepository.save(airplane);
	}
	
	@DeleteMapping("/deleteAirplane/{id}")
	public String deleteAirplane(@PathVariable String id) {
		try {
			Integer aid = Integer.parseInt(id);
			if(airplaneRepository.existsById(aid)) {
				airplaneRepository.deleteById(aid);
				return "Airplane with ID " + " has beed deleted.";
			}
			else {
				return "No airplane found with ID: " + aid; 
			}
		}
		catch(NumberFormatException e) {
			return "Invalid ID format: '" + id + "'. Must be an integer.";
		}
	}
	@PatchMapping("/updateAirplane/{id}")
	public ResponseEntity<String>updateAirplane(@PathVariable String id, @RequestBody Airplane air){
		try {
			Integer aid = Integer.parseInt(id);
			Airplane airplane = airplaneRepository.findById(aid).orElse(null);
			if(airplane == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No airplane found with ID: " + aid);
			}
			if(air.getModel() != null) {
				airplane.setModel(air.getModel());
			}
			if(air.getSeatAmount() != null) {
				airplane.setSeatAmount(air.getSeatAmount());
			}
			airplaneRepository.save(airplane);
			return ResponseEntity.ok("Airplane with ID " + aid + " updated successfully.");
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	@PutMapping("/putAirplane/{id}")
	public ResponseEntity<String>putAirplane(@PathVariable String id, @RequestBody Airplane air){
		try {
			Integer aid = Integer.parseInt(id);
			Airplane airplane = airplaneRepository.findById(aid).orElse(null);
			if(airplane == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No airplane found with ID: " + aid);
			}
			if(!isObjectComplete(air)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                .body("In PutMapping you have to pass full object!");
			}
			airplane.setModel(air.getModel());
			airplane.setSeatAmount(air.getSeatAmount());
			airplaneRepository.save(airplane);
			return ResponseEntity.ok("Airplane with ID " + aid + " updated successfully.");
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	private boolean isObjectComplete(Airplane air) {
		return air.getModel() != null &&
				air.getSeatAmount() != null;
	}
}



















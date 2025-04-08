package com.management.Controllers;

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
import com.management.Model.Airport;
import com.management.Repositories.AirportRepository;

@RestController
@RequestMapping("/airports")
public class AirportController {
	@Autowired
	AirportRepository airportRepository;
	
	@GetMapping("/findAirportById/{id}")
	public @ResponseBody ResponseEntity<Object> getAirport(@PathVariable String id){
		try {
			Integer aid = Integer.parseInt(id);
			Airport airport = airportRepository.findById(aid).orElse(null);
			if(airport == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Couldn't find airport with ID: " + aid);
			}
			return ResponseEntity.ok(airport);
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
	
	@GetMapping("/findAllAirports")
	public @ResponseBody Iterable<Airport>getAirports(){
		return airportRepository.findAll();
	}
	
	@PostMapping("/addAirport")
	public Airport addAirport(@RequestBody Airport airport) {
		return airportRepository.save(airport);
	}
	
	@DeleteMapping("/deleteAirport/{id}")
	public String deleteAirport(@PathVariable String id) {
		try {
			Integer aid = Integer.parseInt(id);
			if(airportRepository.existsById(aid)) {
				airportRepository.deleteById(aid);
				return "Airport with ID " + " has beed deleted.";
			}
			else {
				return "No airport found with ID: " + aid; 
			}
		}
		catch(NumberFormatException e) {
			return "Invalid ID format: '" + id + "'. Must be an integer.";
		}
	}
}

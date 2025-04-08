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
		Airport airport = airportRepository.findById(id).orElse(null);
		if(airport == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Couldn't find airport with ID: " + id);
		}
		return ResponseEntity.ok(airport);
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
		if(airportRepository.existsById(id)) {
			airportRepository.deleteById(id);
			return "Airport with ID " + " has beed deleted.";
		}
		else {
			return "No airport found with ID: " + id; 
		}
	}
}

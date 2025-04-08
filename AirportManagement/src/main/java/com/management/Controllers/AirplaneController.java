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

import com.management.Model.Airplane;
import com.management.Repositories.AirplaneRepository;


@RestController
@RequestMapping("/airplanes")
public class AirplaneController {
	@Autowired 
	AirplaneRepository airplaneRepository;
	
	
	@GetMapping("/findAirplaneById/{id}")
	public @ResponseBody ResponseEntity<Object> getAirplane(@PathVariable String id) {
		Airplane airplane = airplaneRepository.findById(id).orElse(null);
		if(airplane == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Couldn't find airplane with ID: " + id);
		}
		return ResponseEntity.ok(airplane);
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
		if(airplaneRepository.existsById(id)) {
			airplaneRepository.deleteById(id);
			return "Airplane with ID " + " has beed deleted.";
		}
		else {
			return "No airplane found with ID: " + id; 
		}
	}
}

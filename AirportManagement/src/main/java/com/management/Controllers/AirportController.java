package com.management.Controllers;

import java.util.HashSet;
import java.util.List;

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
import com.management.Model.Flight;
import com.management.Model.Passenger;
import com.management.Repositories.AirportRepository;
import com.management.Repositories.FlightRepository;

@RestController
@RequestMapping("/airports")
public class AirportController {
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
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
	public ResponseEntity<String> deleteAirport(@PathVariable String id) {
		try {
			Integer aid = Integer.parseInt(id);
			Airport airport = airportRepository.findById(aid).orElse(null);
			if(airport == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("No airport found with ID: " + aid);
			}
			List<Flight>relatedFlights = flightRepository.findByDepartureAirportOrArrivalAirport(airport, airport);
			for(Flight f : relatedFlights) {
				for(Passenger p : new HashSet<>(f.getPassengerList())) {
					p.getFlights().remove(f);
				}
				f.getPassengerList().clear();
				flightRepository.delete(f);
			}
			airportRepository.delete(airport);
			return ResponseEntity.ok("Airport and related flights deleted successfully.");
		}
		catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid ID format: '" + id + "'. Must be an integer.");
		}
	}
}

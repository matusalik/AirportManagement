package com.management.Controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.DTO.FlightDTO;
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
}

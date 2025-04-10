package com.management.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.management.Model.Airport;
import com.management.Model.Flight;

public interface FlightRepository extends CrudRepository<Flight, Integer>{
	List<Flight> findByDepartureAirportOrArrivalAirport(Airport departure, Airport arrival);
}

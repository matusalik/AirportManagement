package com.management.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.management.Model.Airplane;
import com.management.Model.Airport;
import com.management.Model.Flight;
import com.management.Model.Passenger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDTO {
	Integer idFlight;
	LocalDate departureDate;
	LocalDate arrivalDate;
	LocalTime chekIn;
	Airport departureAirport;
	Airport arrivalAirport;
	Airplane airplane;
	List<Integer>passengerList;
	
	public FlightDTO(Flight flight) {
		this.idFlight = flight.getIdFlight();
		this.departureDate = flight.getDepartureDate();
		this.arrivalDate = flight.getArrivalDate();
		this.chekIn = flight.getCheckIn();
		this.departureAirport = flight.getDepartureAirport();
		this.arrivalAirport = flight.getArrivalAirport();
		this.airplane = flight.getAirplane();
		this.passengerList = flight.getPassengerList().stream()
				.map(Passenger::getIdPassenger)
				.collect(Collectors.toList());	
	}
}

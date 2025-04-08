package com.management.Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Flight {
	@Id
	int idFlight;
	
	LocalDate departureDate;
	
	LocalDate arrivalDate;
	
	LocalTime checkIn;
	
	@ManyToOne
	Airport departureAirport;
	
	@ManyToOne
	Airport arrivalAirport;
	
	@ManyToOne
	Airplane airplane;
	
	@ManyToMany
	Set<Passenger>passengerList;
}

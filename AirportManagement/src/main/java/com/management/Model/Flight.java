package com.management.Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "DEPARTURE_AIRPORT_ID_AIRPORT", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL) 
	Airport departureAirport;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "ARRIVAL_AIRPORT_ID_AIRPORT", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL) 
	Airport arrivalAirport;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "AIRPLANE_ID_AIRPLANE", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL) 
	Airplane airplane;
	
	@ManyToMany
	Set<Passenger>passengerList;
}

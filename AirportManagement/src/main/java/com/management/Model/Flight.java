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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idFlight;
	
	LocalDate departureDate;
	
	LocalDate arrivalDate;
	
	LocalTime checkIn;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTURE_AIRPORT_ID_AIRPORT")
	Airport departureAirport;
	
	@ManyToOne
	@JoinColumn(name = "ARRIVAL_AIRPORT_ID_AIRPORT")
	Airport arrivalAirport;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "AIRPLANE_ID_AIRPLANE", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL) 
	Airplane airplane;
	
	@ManyToMany
	@JoinTable(
		name = "FLIGHT_PASSENGER",
		joinColumns = @JoinColumn(name = "ID_FLIGHT"),
		inverseJoinColumns = @JoinColumn(name = "ID_PASSENGER")
	)
	Set<Passenger>passengerList;
}

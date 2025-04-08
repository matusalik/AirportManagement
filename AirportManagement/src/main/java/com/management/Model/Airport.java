package com.management.Model;
import lombok.*;


import jakarta.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idAirport;
	
	String airportCode;
	
	String city;
}

package com.management.Model;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idPassenger;
	
	String passportId;
	
	String name;
	
	String surname;
	
	LocalDate birthDate;
	
	@ManyToMany
	Set<Flight> flights;
}
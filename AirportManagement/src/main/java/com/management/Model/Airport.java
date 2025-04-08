package com.management.Model;
import lombok.*;


import jakarta.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airport {
	@Id
	String idAirport;
	
	String city;
}

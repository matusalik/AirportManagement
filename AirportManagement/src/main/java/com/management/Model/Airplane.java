package com.management.Model;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airplane {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idAirplane;
	
	int seatAmount;
	
	String model;
}

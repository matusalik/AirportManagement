package com.management.Model;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airplane {
	@Id
	String idAirplane;
	
	int seatAmount;
	
	String model;
}

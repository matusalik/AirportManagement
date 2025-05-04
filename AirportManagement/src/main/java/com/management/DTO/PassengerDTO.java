package com.management.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.management.Model.Flight;
import com.management.Model.Passenger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassengerDTO {
	Integer idPassenger;
	String passportId;
	String name;
	String surname;
	LocalDate birthDate;
	List<Integer>flightIds;
	
	public PassengerDTO(Passenger passenger) {
		this.idPassenger = passenger.getIdPassenger();
        this.passportId = passenger.getPassportId();
        this.name = passenger.getName();
        this.surname = passenger.getSurname();
        this.birthDate = passenger.getBirthDate();
        this.flightIds = passenger.getFlights().stream()
            .map(Flight::getIdFlight)
            .collect(Collectors.toList());
	}
}

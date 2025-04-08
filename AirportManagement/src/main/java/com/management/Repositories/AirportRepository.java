package com.management.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.management.Model.Airplane;
import com.management.Model.Airport;

public interface AirportRepository extends CrudRepository<Airport, String>{

}

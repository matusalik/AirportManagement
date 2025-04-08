package com.management.Repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.Model.Airplane;

public interface AirplaneRepository extends CrudRepository<Airplane, Integer>{

}

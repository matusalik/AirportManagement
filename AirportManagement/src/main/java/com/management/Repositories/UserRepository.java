package com.management.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.management.Users.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Integer>{

}

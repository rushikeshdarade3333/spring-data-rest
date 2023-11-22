package com.example.springdatarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.springdatarest.entity.AppUser;

@RepositoryRestResource(path="appusers")
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	// Custom query methods for searching by first name and last name
    AppUser findByFirstName(String firstName);
    
    AppUser findByEmail(String email);


}

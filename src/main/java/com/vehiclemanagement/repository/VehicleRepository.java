package com.vehiclemanagement.repository;

import com.vehiclemanagement.domain.Vehicle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Vehicle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    Optional<Vehicle> findById(String id);
}

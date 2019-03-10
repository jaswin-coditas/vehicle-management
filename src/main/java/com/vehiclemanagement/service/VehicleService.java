package com.vehiclemanagement.service;

import com.vehiclemanagement.domain.Owner;
import com.vehiclemanagement.domain.Vehicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Vehicle.
 */
public interface VehicleService {

    /**
     * Save a vehicle.
     *
     * @param vehicle the entity to save
     * @return the persisted entity
     */
    Vehicle addVehicleDetails(Vehicle vehicle);


    Vehicle updateVehicleDetails(Vehicle vehicle);

    /**
     * Get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Vehicle> getAllVechicles(Pageable pageable);


    Page<Vehicle> getVehiclesByOwner(Pageable pageable, Owner owner);

    /**
     * Get the "id" vehicle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Vehicle> findOne(String id);

    /**
     * Delete the "id" vehicle.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}

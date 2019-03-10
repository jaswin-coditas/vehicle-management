package com.vehiclemanagement.service.impl;

import com.vehiclemanagement.constants.GeneralConstants;
import com.vehiclemanagement.domain.Owner;
import com.vehiclemanagement.service.VehicleService;
import com.vehiclemanagement.domain.Vehicle;
import com.vehiclemanagement.repository.VehicleRepository;
import com.vehiclemanagement.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Vehicle.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Save a vehicle.
     *
     * @param vehicle the entity to save
     * @return the persisted entity
     */
    @Override
    public Vehicle addVehicleDetails(Vehicle vehicle) {
        log.debug("Request to save Vehicle : {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleDetails(Vehicle vehicle) {
        log.debug("Request to save Vehicle : {}", vehicle);
        Optional<Vehicle> vehiclePlaceHolder = findOne(vehicle.getId());
        if(!vehiclePlaceHolder.isPresent()) {
            throw new BadRequestAlertException(GeneralConstants.INVALID_ID, StringUtils.EMPTY,StringUtils.EMPTY);
        }
        Vehicle updatedVehicle = vehiclePlaceHolder.get();
        if(null != vehicle.getOwner()) {
            updatedVehicle.setOwner(vehicle.getOwner());
        }
        if(null != vehicle.getBrand()) {
            updatedVehicle.setBrand(vehicle.getBrand());
        }
        if(null != vehicle.getVehicleNum()) {
            updatedVehicle.setBrand((vehicle.getBrand()));
        }
        if(null != vehicle.getVehicleType()) {
            updatedVehicle.setVehicleType(vehicle.getVehicleType());
        }
        return vehicleRepository.save(vehicle);
    }
    /**
     * Get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Vehicle> getAllVechicles(Pageable pageable) {
        log.debug("Request to get all Vehicles");
        return vehicleRepository.findAll(pageable);
    }

    public Page<Vehicle> getVehiclesByOwner(Pageable pageable, Owner owner) {
        log.debug("Request to get all Vehicles");
        return vehicleRepository.findByOwner(pageable,owner);
    }

    /**
     * Get one vehicle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Vehicle> findOne(String id) {
        log.debug("Request to get Vehicle : {}", id);
        return vehicleRepository.findById(id);
    }

    /**
     * Delete the vehicle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.deleteById(id);
    }
}

package com.vehiclemanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.vehiclemanagement.constants.GeneralConstants;
import com.vehiclemanagement.constants.MessageConstants;
import com.vehiclemanagement.domain.Vehicle;
import com.vehiclemanagement.service.VehicleService;
import com.vehiclemanagement.service.dto.CustomResponseDTO;
import com.vehiclemanagement.service.dto.PageDto;
import com.vehiclemanagement.web.rest.errors.BadRequestAlertException;
import com.vehiclemanagement.web.rest.util.HeaderUtil;
import com.vehiclemanagement.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Vehicle.
 */
@RestController
@RequestMapping("/api")
public class VehicleResource {

    private final Logger log = LoggerFactory.getLogger(VehicleResource.class);

    private static final String ENTITY_NAME = "vehicle";

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * POST  /vehicles : Create a new vehicle.
     *
     * @param vehicle the vehicle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicle, or with status 400 (Bad Request) if the vehicle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicles")
    @Timed
    public  ResponseEntity<CustomResponseDTO> createVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        log.debug("REST request to save Vehicle : {}", vehicle);

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            if (vehicle.getId() != null) {
                throw new BadRequestAlertException(GeneralConstants.NEW_ENTITY_CAN_NOT_ALREADY_HAVE_ID, ENTITY_NAME, GeneralConstants.ID_EXISTS);
            }
            Vehicle createdVechicle = vehicleService.addVehicleDetails(vehicle);
            customResponseDTO.setData(createdVechicle);
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        } catch(BadRequestAlertException badRequestAlertException) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(badRequestAlertException.getMessage());
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());

    }

    /**
     * PUT  /vehicles : Updates an existing vehicle.
     *
     * @param vehicle the vehicle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicle,
     * or with status 400 (Bad Request) if the vehicle is not valid,
     * or with status 500 (Internal Server Error) if the vehicle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicles")
    @Timed
    public ResponseEntity<CustomResponseDTO> updateVehicle(@RequestBody Vehicle vehicle) throws URISyntaxException {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            if (vehicle.getId() == null) {
                throw new BadRequestAlertException(GeneralConstants.ID_REQUIRED_TO_UPDATE_AN_ENTITY, ENTITY_NAME, StringUtils.EMPTY);
            }
            Vehicle createdVechicle = vehicleService.updateVehicleDetails(vehicle);
            customResponseDTO.setData(createdVechicle);
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        } catch(BadRequestAlertException badRequestAlertException) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(badRequestAlertException.getMessage());
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());
    }

    /**
     * GET  /vehicles : get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vehicles in body
     */
    @GetMapping("/vehicles")
    @Timed
    public ResponseEntity<CustomResponseDTO> getAllVehicles(Pageable pageable) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        PageDto<Vehicle> vehiclePageDto = new PageDto<Vehicle>();
        try {
            Page<Vehicle> vehiclePage = vehicleService.getAllVechicles(pageable);
            vehiclePageDto.setCurrentPageData(vehiclePage.getContent());
            vehiclePageDto.setTotalCount(vehiclePage.getTotalElements());
            customResponseDTO.setData(vehiclePageDto);
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        } catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());
    }

    /**
     * GET  /vehicles/:id : get the "id" vehicle.
     *
     * @param id the id of the vehicle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicle, or with status 404 (Not Found)
     */
    @GetMapping("/vehicles/{id}")
    @Timed
    public ResponseEntity<CustomResponseDTO> getVehicle(@PathVariable String id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            Optional<Vehicle> vehicle = vehicleService.findOne(id);
            if(!vehicle.isPresent()) {
                throw new BadRequestAlertException(GeneralConstants.ENTITY_NOT_FOUND_WITH_GIVEN_ID,ENTITY_NAME,StringUtils.EMPTY);
            }
            customResponseDTO.setData(vehicle.get());
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        }  catch(BadRequestAlertException badRequestAlertException) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(badRequestAlertException.getMessage());
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }  catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());
    }

    /**
     * DELETE  /vehicles/:id : delete the "id" vehicle.
     *
     * @param id the id of the vehicle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicles/{id}")
    @Timed
    public ResponseEntity<CustomResponseDTO> deleteVehicle(@PathVariable String id) {
        log.debug("REST request to delete Vehicle : {}", id);
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            vehicleService.delete(id);
            customResponseDTO.setData(null);
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        }  catch(BadRequestAlertException badRequestAlertException) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(badRequestAlertException.getMessage());
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }  catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());
    }
}

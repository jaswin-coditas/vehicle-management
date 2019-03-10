package com.vehiclemanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.vehiclemanagement.constants.GeneralConstants;
import com.vehiclemanagement.constants.MessageConstants;
import com.vehiclemanagement.domain.Owner;
import com.vehiclemanagement.domain.Vehicle;
import com.vehiclemanagement.service.OwnerService;
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
 * REST controller for managing Owner.
 */
@RestController
@RequestMapping("/api")
public class OwnerResource {

    private final Logger log = LoggerFactory.getLogger(OwnerResource.class);

    private static final String ENTITY_NAME = "owner";

    private final OwnerService ownerService;

    public OwnerResource(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * POST  /owners : Create a new owner.
     *
     * @param owner the owner to create
     * @return the ResponseEntity with status 201 (Created) and with body the new owner, or with status 400 (Bad Request) if the owner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/owners")
    @Timed
    public ResponseEntity<CustomResponseDTO> createOwner(@RequestBody Owner owner) throws URISyntaxException {
        log.debug("REST request to save Vehicle : {}", owner);

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            if (owner.getId() != null) {
                throw new BadRequestAlertException(GeneralConstants.NEW_ENTITY_CAN_NOT_ALREADY_HAVE_ID, ENTITY_NAME, GeneralConstants.ID_EXISTS);
            }
            Owner addedOwner = ownerService.addOwner(owner);
            customResponseDTO.setData(addedOwner);
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
     * PUT  /owners : Updates an existing owner.
     *
     * @param owner the owner to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated owner,
     * or with status 400 (Bad Request) if the owner is not valid,
     * or with status 500 (Internal Server Error) if the owner couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/owners")
    @Timed
    public ResponseEntity<CustomResponseDTO> updateOwner(@RequestBody Owner owner) throws URISyntaxException {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            if (owner.getId() == null) {
                throw new BadRequestAlertException(GeneralConstants.ID_REQUIRED_TO_UPDATE_AN_ENTITY, ENTITY_NAME, StringUtils.EMPTY);
            }
            Owner updateOwner = ownerService.updateOwner(owner);
            customResponseDTO.setData(updateOwner);
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
     * GET  /owners : get all the owners.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of owners in body
     */
    @GetMapping("/owners")
    @Timed
    public ResponseEntity<CustomResponseDTO> getAllOwners(Pageable pageable) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        PageDto<Owner> ownerPageDto = new PageDto<Owner>();
        try {
            Page<Owner> ownerPage = ownerService.findAll(pageable);
            ownerPageDto.setCurrentPageData(ownerPage.getContent());
            ownerPageDto.setTotalCount(ownerPage.getTotalElements());
            customResponseDTO.setData(ownerPageDto);
            customResponseDTO.setHttpStatus(HttpStatus.OK);
        } catch (Exception exception) {
            customResponseDTO.setSuccess(false);
            customResponseDTO.setMessage(MessageConstants.ERROR);
            customResponseDTO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomResponseDTO>(customResponseDTO, customResponseDTO.getHttpStatus());
    }

    /**
     * GET  /owners/:id : get the "id" owner.
     *
     * @param id the id of the owner to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the owner, or with status 404 (Not Found)
     */
    @GetMapping("/owners/{id}")
    @Timed
    public ResponseEntity<CustomResponseDTO> getOwner(@PathVariable String id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            Optional<Owner> owner = ownerService.findOne(id);
            if(!owner.isPresent()) {
                throw new BadRequestAlertException(GeneralConstants.ENTITY_NOT_FOUND_WITH_GIVEN_ID,ENTITY_NAME,StringUtils.EMPTY);
            }
            customResponseDTO.setData(owner.get());
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
     * DELETE  /owners/:id : delete the "id" owner.
     *
     * @param id the id of the owner to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/owners/{id}")
    @Timed
    public ResponseEntity<CustomResponseDTO> deleteOwner(@PathVariable String id) {
        log.debug("REST request to delete owner : {}", id);
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        try {
            ownerService.delete(id);
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

package com.vehiclemanagement.service.impl;

import com.vehiclemanagement.constants.GeneralConstants;
import com.vehiclemanagement.domain.Vehicle;
import com.vehiclemanagement.service.OwnerService;
import com.vehiclemanagement.domain.Owner;
import com.vehiclemanagement.repository.OwnerRepository;
import com.vehiclemanagement.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Owner.
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    private final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Save a owner.
     *
     * @param owner the entity to save
     * @return the persisted entity
     */
    @Override
    public Owner addOwner(Owner owner) {
        log.debug("Request to add new Owner : {}", owner);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        log.debug("Request to update Owner : {}", owner);
        Optional<Owner> ownerPlaceHolder = findOne(owner.getId());
        if(!ownerPlaceHolder.isPresent()) {
            throw new BadRequestAlertException(GeneralConstants.INVALID_ID, StringUtils.EMPTY,StringUtils.EMPTY);
        }
        Owner updatedOwner = ownerPlaceHolder.get();
        if(null != owner.getFirstName()) {
            updatedOwner.setFirstName(owner.getFirstName());
        }
        if(null != owner.getLastName()) {
            updatedOwner.setLastName(owner.getLastName());
        }
        if(null != owner.getMiddleName()) {
            updatedOwner.setMiddleName(owner.getMiddleName());
        }
        if(null != owner.getMobileNum()) {
            updatedOwner.setMobileNum(owner.getMobileNum());
        }
        if(null != owner.getAlterNateMobileNo()) {
            updatedOwner.setAlterNateMobileNo(owner.getAlterNateMobileNo());
        }
        if(null != owner.getOrgEmail()) {
            updatedOwner.setOrgEmail(owner.getOrgEmail());
        }
        if(null != owner.getPersonalEmailOne()) {
            updatedOwner.setPersonalEmailOne(owner.getPersonalEmailOne());
        }
        if(null != owner.getPersonalEmailTwo()) {
            updatedOwner.setPersonalEmailTwo(owner.getPersonalEmailTwo());
        }
        return ownerRepository.save(updatedOwner);
    }
    /**
     * Get all the owners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<Owner> findAll(Pageable pageable) {
        log.debug("Request to get all Owners");
        return ownerRepository.findAll(pageable);
    }


    /**
     * Get one owner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<Owner> findOne(String id) {
        log.debug("Request to get Owner : {}", id);
        return ownerRepository.findById(id);
    }

    /**
     * Delete the owner by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Owner : {}", id);
        ownerRepository.deleteById(id);
    }
}

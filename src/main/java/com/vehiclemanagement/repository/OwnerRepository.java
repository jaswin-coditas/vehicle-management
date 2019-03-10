package com.vehiclemanagement.repository;

import com.vehiclemanagement.domain.Owner;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Owner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {

}

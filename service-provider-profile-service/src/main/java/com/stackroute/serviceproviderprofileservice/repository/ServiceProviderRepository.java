package com.stackroute.serviceproviderprofileservice.repository;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends MongoRepository<ServiceProvider,String> {
    public ServiceProvider findByEmailId(String emailId);
}

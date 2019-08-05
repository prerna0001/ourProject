package com.stackroute.intelligentservice.repository;

import com.stackroute.intelligentservice.domain.IntelligentService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IntelligentServiceRepository extends MongoRepository<IntelligentService,String> {


    public IntelligentService findByRole(String role);  //repo method to find data by role



}

package com.stackroute.innovatormanagementservice.repository;

import com.stackroute.innovatormanagementservice.domain.Idea;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagementRepo extends MongoRepository<Idea,String> {
    Idea findByTitle(String title);
}

package com.stackroute.repository;

import com.stackroute.domain.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface IdeaHubRepository extends MongoRepository<Idea,String> {
        @Override
        boolean existsById(String s);                           //method for checking if idea exists by ID
        Idea findByTitle(String title);                         //Method to find an idea by its title
        public List<Idea> findByEmailId(String emailId);        //Method to find an idea by its corresponding email id

}


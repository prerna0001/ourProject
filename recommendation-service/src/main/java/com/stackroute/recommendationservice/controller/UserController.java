package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/user/")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping
    public Collection<User> getAll() {

        return userServices.getAll();
    }


    @GetMapping("{name}")
    public  String getUser(@PathVariable String name){
        String output=userServices.getByName(name);
        return output;
    }


    @GetMapping("recommendations/{subDomain}")
    public Collection<User> getAllUsers(@PathVariable String subDomain) {

        return userServices.getAllUsersBy(subDomain);
    }


    @PostMapping("/saved")
    public ResponseEntity<?> savedUser(@RequestBody User user)
        {
            return new ResponseEntity<User>(userServices.saved(user), HttpStatus.CREATED);
        }


    @PostMapping("/{subDomainName}/{name}")
    public  User getUserSubDomain(@PathVariable String subDomainName,@PathVariable String name){

        return userServices.matchUserSubDomain(subDomainName,name);
    }

    @PostMapping("/{ideaName}/{name}")
    public  User getUserIdea(@PathVariable String ideaName,@PathVariable String name){

        return userServices.matchIdea(ideaName,name);
    }



}
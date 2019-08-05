package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.SubDomain;
import com.stackroute.recommendationservice.service.SubDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/sub")
public class SubDomainController {

    @Autowired
    public SubDomainService subDomainService;


    @GetMapping("/getall")
    public Collection<SubDomain> getAll() {

        return subDomainService.getAll();
    }


    //To save subDomain
    @PostMapping("/saved")
    public ResponseEntity<?> savedSubDomain(@RequestBody SubDomain subDomain)
    {
        subDomainService.saved(subDomain);
        return new ResponseEntity<SubDomain>(subDomainService.saved(subDomain), HttpStatus.CREATED);
    }







}

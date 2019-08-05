package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.model.Idea;
import com.stackroute.recommendationservice.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/neo4j/idea")

public class IdeaController {

    @Autowired
    IdeaService ideaService;


    //to get list of all ideas
    @GetMapping("/getall")
    public Collection<Idea> getAll() {

        return ideaService.getAll();
    }

    //To save idea
    @PostMapping("/saved")
    public ResponseEntity<?> savedIdea(@RequestBody Idea idea)
    {
        return new ResponseEntity<Idea>(ideaService.saved(idea), HttpStatus.CREATED);
    }

    //To match subDomain and ideaName
    @PostMapping("/{subDomainName}/{ideaName}")
    public  Idea getIdeaSubDomain(@PathVariable String subDomainName,@PathVariable String ideaName){

        return ideaService.matchSubDomain(subDomainName,ideaName);
    }


    //To get Ideas related to specific role
    @GetMapping("getIdea/{role}")
    public List<Idea> getIdea(@PathVariable String role) {
        return ideaService.getIdea(role);


    }

    //recommending ideas to users by retrieving their role
    @GetMapping("ideas/{emailId}")
    public List<Idea> getRecommendedIdeas(@PathVariable String emailId){
        return ideaService.getRecommendedIdeas(emailId);
    }

}

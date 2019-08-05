package com.stackroute.controller;


import com.stackroute.domain.Idea;
import com.stackroute.dto.IdeaDto;
import com.stackroute.exceptions.IdeaNotFoundException;
import com.stackroute.exceptions.NullIdeaException;
import com.stackroute.service.IdeaHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class IdeaHubController {

    private IdeaHubService ideaHubService;
    //          CONSTRUCTOR AUTOWIRING
    @Autowired
    public IdeaHubController(IdeaHubService ideaService) {

        this.ideaHubService = ideaService;
    }
    //          API FOR ADDING AN IDEA
    @PostMapping("/saved")
    public ResponseEntity<?> addIdea(@RequestBody Idea idea) {
        ResponseEntity responseEntity;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dddd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            dateFormat.format(date);
            idea.setTimestamp(date);

            ideaHubService.send(idea);
            Idea addedIdea = ideaHubService.addIdea(idea);
            return new ResponseEntity<Idea>(addedIdea, HttpStatus.CREATED);
        }
        catch (Exception e)
        { e.getMessage();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);}
    }
    //          API FOR RETRIEVING ALL IDEAS
    @GetMapping("/ideas")
    public ResponseEntity<List<Idea>> getIdeas(Idea idea)
    {
        ResponseEntity responseEntity;
        try{
            return new ResponseEntity<List<Idea>>(ideaHubService.displayIdea(idea), HttpStatus.OK);
        }
        catch (NullIdeaException e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseEntity;
    }
    //          API FOR RETRIEVING AN IDEA BY ITS ID
    @GetMapping("/idea{id}")
    public ResponseEntity<Optional>getIdea(Idea idea) {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<>(ideaHubService.findIdeaById(idea), HttpStatus.FOUND);
        }
        catch (IdeaNotFoundException e)
        {
            e.getMessage();
            responseEntity=new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //          API FOR DELETING AN IDEA BY ITS ID
    @DeleteMapping("/deleted/{ideaId}")
    public  ResponseEntity<String> deleteIdea(@PathVariable String ideaId) {
        ResponseEntity responseEntity;
        try {
            ideaHubService.deleteIdea(ideaId);
            return new ResponseEntity<String>("successfully deleted", HttpStatus.GONE);
        }
        catch (IdeaNotFoundException e)
        { responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);}
        return responseEntity;
    }

    //          API FOR UPDATING IDEA DETAILS(ARRAY OF SERVICE PROVIDERS)
    @PutMapping("/idea")
    public ResponseEntity<?> updateIdea(@RequestBody Idea idea) {
        ResponseEntity responseEntity;

        try {
            return new ResponseEntity<Idea>(ideaHubService.updateIdea(idea.getTitle(), idea.getServiceProviders()), HttpStatus.ACCEPTED);
        }
        catch (IdeaNotFoundException e)
        { responseEntity=new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    //          API FOR FINDING AN IDEA BY EMAIL-ID ASSOCIATED WITH IT
    @GetMapping("/getIdeaByEmailId/{emailId}")
    public ResponseEntity<?> getByEmailId(@PathVariable String emailId)
    {
        try {
            return new ResponseEntity<>(ideaHubService.getByEmailId(emailId), HttpStatus.OK);
        }
        catch (IdeaNotFoundException e)
        {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    //          API FOR RETRIEVING AN IDEA BY TITLE
    @GetMapping("/idea/{title}")
    public ResponseEntity<?> getIdeaByTitle(@PathVariable String title)throws IdeaNotFoundException {
        try{
            return new ResponseEntity<Idea>(ideaHubService.getByIdeaTitle(title),HttpStatus.OK);
        }
        catch (IdeaNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //method will called by idea-management service to update approved service provider
    @PutMapping("/ideaDto")
    public Idea updateServiceProviders(@RequestBody IdeaDto ideaDto){
        return ideaHubService.updateApprovedServiceProvider(ideaDto);
    }

}

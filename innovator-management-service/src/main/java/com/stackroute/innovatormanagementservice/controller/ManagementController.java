package com.stackroute.innovatormanagementservice.controller;

import com.stackroute.innovatormanagementservice.domain.Idea;
import com.stackroute.innovatormanagementservice.domain.ServiceProvider;
import com.stackroute.innovatormanagementservice.exception.EmailIdAlreadyExistsException;
import com.stackroute.innovatormanagementservice.exception.IdeaNotFoundException;
import com.stackroute.innovatormanagementservice.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/")
public class ManagementController {
    private ManagementService managementService;
    private ResponseEntity responseEntity;


    @Autowired
    public ManagementController(ManagementService managementService ) {
        this.managementService = managementService;
    }
//this method is called when service provider applies for an idea
    @PutMapping("/idea/{title}/{emailId}")
    public ResponseEntity<?> updateAppliedUsers(@PathVariable String title,@PathVariable String emailId)throws IdeaNotFoundException , EmailIdAlreadyExistsException {
        try{
             responseEntity = new ResponseEntity<Idea>(managementService.updateIdea(title,emailId), HttpStatus.ACCEPTED);
        }
        catch (IdeaNotFoundException e){
         responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (EmailIdAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    //This method is for getting all applied service providers for  an idea
    @GetMapping("/serviceProviders/{title}")
    public ResponseEntity<?> getAppliedUsers(@PathVariable String title)throws IdeaNotFoundException {
        try {
            responseEntity = new ResponseEntity<List<ServiceProvider>>(managementService.getAppliedServiceProviders(title), HttpStatus.OK);
        } catch (IdeaNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }
    //called when clicked on approve or reject button
    @PutMapping("/serviceProviders/{title}/{emailId}/{status}")
    public ResponseEntity<?> removeAppliedUsers(@PathVariable String title, @PathVariable String emailId, @PathVariable boolean status)throws Exception{
        try{
            responseEntity = new ResponseEntity<Idea>(managementService.deleteAppliedServiceProvider(title,emailId,status),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}

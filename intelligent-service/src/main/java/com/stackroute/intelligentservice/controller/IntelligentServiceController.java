package com.stackroute.intelligentservice.controller;

import com.stackroute.intelligentservice.domain.IntelligentService;
import com.stackroute.intelligentservice.exception.RoleNotFoundException;
import com.stackroute.intelligentservice.service.IntelligentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")   //to connect backaend with front-end
@RequestMapping("/api/v1")
public class IntelligentServiceController {
    @Autowired
    IntelligentServiceInterface intelligentSeviceInterface;

    public IntelligentServiceInterface getIntelligentSeviceInterface() {
        return intelligentSeviceInterface;
    }

    public void setIntelligentSeviceInterface(IntelligentServiceInterface intelligentSeviceInterface) {
        this.intelligentSeviceInterface = intelligentSeviceInterface;
    }


    @GetMapping("/intelligentService/{role}")    //getMapping to retrive whole document with specific role
    public ResponseEntity<?> getByRole(@PathVariable String role) throws RoleNotFoundException
    {
        ResponseEntity responseEntity=null;
        try {

            return new ResponseEntity<IntelligentService>(intelligentSeviceInterface.getByRole(role), HttpStatus.OK);
        }
        catch (RoleNotFoundException ex)
        {
            responseEntity= new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
            ex.getMessage();
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        return responseEntity;
    }



}

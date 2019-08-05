package com.stackroute.serviceproviderprofileservice.controller;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdAlreadyExistsException;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdNotFoundException;
import com.stackroute.serviceproviderprofileservice.service.ServiceProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1")
public class ServiceProviderController
{
    private ServiceProviderServiceImpl serviceProviderServiceimpl;

    @Autowired
    public ServiceProviderController(ServiceProviderServiceImpl serviceProviderServiceimpl) {
        this.serviceProviderServiceimpl = serviceProviderServiceimpl;
    }
//method to save a service provider profile
    @PostMapping("/serviceprovider")
    public ResponseEntity<?> saveServiceProvider(@RequestBody ServiceProvider serviceProvider) throws EmailIdAlreadyExistsException, Exception
    {
        ResponseEntity responseEntity=null;
        String emailID= serviceProvider.getEmailId();

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if (!emailID.matches(regex) || emailID == null)
        {
            throw new Exception(("EmailId entered by you is not proper or empty!!!"));
        }
        try {
            serviceProviderServiceimpl.send(serviceProvider);
            return new ResponseEntity<ServiceProvider>(serviceProviderServiceimpl.saveServiceProvider(serviceProvider), HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    //method to get all service provider profiles
    @GetMapping("/serviceproviders")
    public ResponseEntity<?> getServiceProviders()
    {
       return  new ResponseEntity<List<ServiceProvider>>(serviceProviderServiceimpl.getServiceProvider(),HttpStatus.OK);
    }

    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<?> getByEmailId(@PathVariable String emailId) throws EmailIdNotFoundException
    {
        ResponseEntity responseEntity=null;
        try{
            return new ResponseEntity<ServiceProvider>(serviceProviderServiceimpl.getByEmailId(emailId),HttpStatus.OK);

        }
        catch (EmailIdNotFoundException ex)
        {
            responseEntity= new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);

        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        return responseEntity;
    }

}



package com.stackroute.intelligentservice.service;

import com.stackroute.intelligentservice.domain.IntelligentService;
import com.stackroute.intelligentservice.exception.RoleNotFoundException;


public interface IntelligentServiceInterface {

    public IntelligentService getByRole(String role) throws RoleNotFoundException;   //method to be used in IntelligentServiceImpl

}

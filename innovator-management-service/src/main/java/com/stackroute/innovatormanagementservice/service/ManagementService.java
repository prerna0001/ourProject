package com.stackroute.innovatormanagementservice.service;

import com.stackroute.innovatormanagementservice.domain.Idea;
import com.stackroute.innovatormanagementservice.domain.ServiceProvider;
import com.stackroute.innovatormanagementservice.exception.EmailIdAlreadyExistsException;
import com.stackroute.innovatormanagementservice.exception.IdeaNotFoundException;

import java.util.List;

public interface ManagementService {
    public Idea updateIdea(String title, String emailId) throws IdeaNotFoundException, EmailIdAlreadyExistsException;
    public List<ServiceProvider> getAppliedServiceProviders(String title) throws IdeaNotFoundException;
    public Idea deleteAppliedServiceProvider(String title,String emailId, boolean status) throws Exception;

}

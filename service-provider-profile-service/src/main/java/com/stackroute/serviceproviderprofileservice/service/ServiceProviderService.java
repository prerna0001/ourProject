package com.stackroute.serviceproviderprofileservice.service;


import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdAlreadyExistsException;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdNotFoundException;

import java.util.List;

public interface ServiceProviderService {
    public ServiceProvider saveServiceProvider(ServiceProvider serviceProvider) throws EmailIdAlreadyExistsException;
    public List<ServiceProvider> getServiceProvider();
    public ServiceProvider getByEmailId(String emailId) throws EmailIdNotFoundException;

    public ServiceProvider  send(ServiceProvider service);
}

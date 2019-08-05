package com.stackroute.serviceproviderprofileservice.service;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdAlreadyExistsException;
import com.stackroute.serviceproviderprofileservice.exceptions.EmailIdNotFoundException;
import com.stackroute.serviceproviderprofileservice.repository.ServiceProviderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService
{
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${serviceProvider.exchange}")
    String servicePExchange;

    @Value("${serviceProvider.routingkey}")
    String servicePRoutingkey;


    @Autowired
    public ServiceProviderServiceImpl(ServiceProviderRepository serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }

    //service implementation to save service provider profile
    @Override
    public ServiceProvider saveServiceProvider(ServiceProvider serviceProvider) throws EmailIdAlreadyExistsException {
        if(serviceProviderRepository.existsById(serviceProvider.getEmailId()))
        {
            throw new EmailIdAlreadyExistsException("This Email ID already exists!!!");
        }
        ServiceProvider savedServiceProvider= serviceProviderRepository.save(serviceProvider);
        if(savedServiceProvider==null)
        {
            throw new EmailIdAlreadyExistsException("This Email ID already exists!!!");
        }
        else
            return savedServiceProvider;
    }

    //service implemetation to get all service provider profiles
    @Override
    public List<ServiceProvider> getServiceProvider() {
        return serviceProviderRepository.findAll();
    }

    @Override
    public ServiceProvider getByEmailId(String emailId) throws EmailIdNotFoundException {
        ServiceProvider serviceProvider= serviceProviderRepository.findByEmailId(emailId);
        if(serviceProvider==null)
        {
            throw new EmailIdNotFoundException("document not found by this emailId!!!");
        }
        else
            return serviceProvider;

    }


    public ServiceProvider send(ServiceProvider serviceProvider) {
        rabbitTemplate.convertAndSend(servicePExchange, servicePRoutingkey, serviceProvider);
        System.out.println("Send msg = " + serviceProvider);
        return serviceProvider;
    }
}


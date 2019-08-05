package com.stackroute.intelligentservice.service;

import com.stackroute.intelligentservice.domain.IntelligentService;
import com.stackroute.intelligentservice.domain.ServiceProvider;
import com.stackroute.intelligentservice.exception.RoleNotFoundException;
import com.stackroute.intelligentservice.repository.IntelligentServiceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntelligentServiceImpl implements IntelligentServiceInterface {

    private IntelligentServiceRepository intelligentServiceRepository;
    private IntelligentService intelligentService;

    @Autowired
    public IntelligentServiceImpl(IntelligentServiceRepository intelligentServiceRepository, IntelligentService intelligentService) {
        this.intelligentServiceRepository = intelligentServiceRepository;
        this.intelligentService = intelligentService;
    }

    @RabbitListener(queues = "${intelligent.queue}")        //rabbitListener to get data from producer->service-Provider
    public IntelligentService recievedMessageFromServiceProvider(ServiceProvider serviceProvider) {

        System.out.println(serviceProvider.toString());

        String role1= serviceProvider.getRole();    //extracting role from producers message

        System.out.println(role1);

        IntelligentService retrievedIntelligentService = intelligentServiceRepository.findByRole(role1);  //calling findByRole() in repository to find data acxcording to role

        System.out.println(retrievedIntelligentService);

        if (retrievedIntelligentService!=null)  //if role exists already in database updating it
        {
            List<ServiceProvider> serviceProviderList=retrievedIntelligentService.getServiceProvider();
            serviceProviderList.add(serviceProvider);
            intelligentServiceRepository.save(retrievedIntelligentService);

        }
        else                                    //if role does not exists in database save it
        {
            String role = serviceProvider.getRole();
            intelligentService.setRole(role);

            List<ServiceProvider> serviceProviders = new ArrayList<>();
            serviceProviders.add(serviceProvider);

            intelligentService.setServiceProvider(serviceProviders);
            intelligentServiceRepository.save(intelligentService);

        }
        System.out.println("Recieved Message From serviceProvider:" + serviceProvider.toString());
        return null;

    }

    @Override
    public IntelligentService getByRole(String role) throws RoleNotFoundException {  //method to find data by role


        IntelligentService retrievedIntelligentService = intelligentServiceRepository.findByRole(role);

        if(retrievedIntelligentService==null)
        {
            throw new RoleNotFoundException("document not found by this role!!! ");
        }
        else
        return intelligentServiceRepository.findByRole(role);
    }
}

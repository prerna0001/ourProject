package com.stackroute.innovatormanagementservice.service;

import com.stackroute.innovatormanagementservice.domain.Idea;
import com.stackroute.innovatormanagementservice.domain.ServiceProvider;
import com.stackroute.innovatormanagementservice.dto.IdeaDto;
import com.stackroute.innovatormanagementservice.exception.EmailIdAlreadyExistsException;
import com.stackroute.innovatormanagementservice.exception.IdeaNotFoundException;
import com.stackroute.innovatormanagementservice.repository.ManagementRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    private ManagementRepo managementRepo;
    private Idea newIdea;
    private Idea ideaDto;
    private RestTemplate restTemplate;
    private ServiceProvider serviceProvider;
    private RabbitTemplate rabbitTemplate;
    private List<ServiceProvider> serviceProviderDto = new ArrayList<>();
    @Value("${serviceProvider.url}")
    String url;
    String ideaRoutingKey;
    @Value("${idea.url}")
    String ideaUrl;


    @Autowired
    public ManagementServiceImpl(ManagementRepo managementRepo, Idea newIdea, ServiceProvider serviceProvider, Idea ideaDto,RestTemplate restTemplate,RabbitTemplate rabbitTemplate) {
        this.managementRepo = managementRepo;
        this.newIdea = newIdea;
        this.serviceProvider = serviceProvider;
        this.ideaDto= ideaDto;
        this.restTemplate =restTemplate;
        this.rabbitTemplate=rabbitTemplate;

    }

    @RabbitListener(queues = "${javainuse.management.queue}")
    public void saveIdea(IdeaDto ideaDto) {

            Idea fetchedIdea = new Idea();
            fetchedIdea.setTitle(ideaDto.getTitle());
            fetchedIdea.setDescription(ideaDto.getDescription());
            fetchedIdea.setBudget(ideaDto.getBudget());
            fetchedIdea.setDomain(ideaDto.getDomain());
            fetchedIdea.setEmailId(ideaDto.getEmailId());
            fetchedIdea.setRoles(ideaDto.getRoles());
            fetchedIdea.setSubDomain(ideaDto.getSubDomain());
            fetchedIdea.setTimestamp(ideaDto.getTimestamp());
            fetchedIdea.setAppliedServiceProviders(ideaDto.getServiceProviders());
            managementRepo.save(fetchedIdea);
    }
//Method to update applied service provider to the list
    @Override
    public Idea updateIdea(String title, String emailId) throws IdeaNotFoundException , EmailIdAlreadyExistsException {
        Idea recievedIdea = managementRepo.findByTitle(title);
        if (recievedIdea == null) {
            throw new IdeaNotFoundException("Idea Not found");
        }

        else {
            serviceProvider = restTemplate.getForObject(url + emailId, ServiceProvider.class);
            List<ServiceProvider> serviceProviderList = recievedIdea.getAppliedServiceProviders();
            if(serviceProviderList == null){
                serviceProviderList = new ArrayList<ServiceProvider>();
            }
            for(int i= 0; i<recievedIdea.getAppliedServiceProviderEmail().size();i++){
                if((recievedIdea.getAppliedServiceProviderEmail().get(i)).equals(emailId)){
                    throw new EmailIdAlreadyExistsException("User Already Applied for this Idea");
                }
            }
            recievedIdea.getAppliedServiceProviderEmail().add(emailId);
            serviceProviderList.add(serviceProvider);
            recievedIdea.setAppliedServiceProviders(serviceProviderList);
            managementRepo.save(recievedIdea);
        }
        return recievedIdea;

    }

    @Override
    public List<ServiceProvider> getAppliedServiceProviders(String title) throws IdeaNotFoundException {
        List<ServiceProvider> appliedServiceProviders;
        Idea recievedIdea = managementRepo.findByTitle(title);
        if (recievedIdea == null) {
            throw new IdeaNotFoundException("Idea Not found");
        } else {
            appliedServiceProviders = recievedIdea.getAppliedServiceProviders();
            return appliedServiceProviders;
        }
    }

    //deletes applied users
    @Override
    public Idea deleteAppliedServiceProvider(String title, String emailId, boolean status) throws Exception {
        Idea newIdea1 = managementRepo.findByTitle(title);
        Idea updatedIdea = new Idea();
        List<ServiceProvider> serviceProviderList = new ArrayList<>();
        serviceProviderList = newIdea1.getAppliedServiceProviders();
        for (int i = 0; i < serviceProviderList.size(); i++) {
            if ((serviceProviderList.get(i).getEmailId()).equals( emailId)) {
                if (status == false) {

                    serviceProviderList.remove(i);
                    newIdea1.setAppliedServiceProviders(serviceProviderList);
                    updatedIdea = managementRepo.save(newIdea1);
                    return updatedIdea;
                } else {
                    ideaDto.setTitle(title);
                    serviceProviderDto.add(serviceProviderList.get(i));
                    ideaDto.setAppliedServiceProviders(serviceProviderDto);
                    System.out.println(ideaUrl);
                    restTemplate.put(ideaUrl,ideaDto);
                    serviceProviderList.remove(i);
                    newIdea1.setAppliedServiceProviders(serviceProviderList);
                    updatedIdea = managementRepo.save(newIdea1);
                    return updatedIdea;

                }
            }
        }
        return updatedIdea;
    }

}

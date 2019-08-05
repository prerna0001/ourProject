package com.stackroute.service;

import com.stackroute.domain.Idea;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.IdeaDto;
import com.stackroute.exceptions.IdeaNotFoundException;
import com.stackroute.exceptions.NullIdeaException;
import com.stackroute.repository.IdeaHubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
@Slf4j
@Service
public class IdeaHubServiceImpl implements IdeaHubService {
    IdeaHubRepository ideaHubRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //      RABBITMQ EXCHANGE
    @Value("${javainuse.idea.exchange}")
    String ideaExchange;
    //      RABBITMQ KEY
    @Value("${javainuse.idea.routingkey}")
    String ideaRoutingkey;


    @Autowired
    public IdeaHubServiceImpl(IdeaHubRepository ideaRepository)
    {
        this.ideaHubRepository = ideaRepository;
    }

    //          CODE FOR ADDING AN IDEA
    @Override
    public Idea addIdea(Idea idea) {
        Idea savedIdea;

        savedIdea = ideaHubRepository.save(idea);
        return savedIdea;

    }

    //          CODE FOR DELETING AN IDEA
    @Override
    public void deleteIdea(String ideaId) throws IdeaNotFoundException {
        if(ideaHubRepository.existsById(ideaId)) {
            ideaHubRepository.deleteById(ideaId);
        }
        throw new IdeaNotFoundException("Idea Not found");
    }


    //          CODE FOR UPDATING AN IDEA
    @Override
    public Idea updateIdea(String title, List<ServiceProvider> serviceproviders) throws IdeaNotFoundException {
        Idea ideaNew=ideaHubRepository.findByTitle(title);
        if (ideaNew==null) {

            throw new IdeaNotFoundException("Entry not found");
        }
        ideaNew.setServiceProviders(serviceproviders);

        return ideaHubRepository.save(ideaNew);

    }
    // CODE FOR FINDING BY ITS ID
    @Override
    public Optional<Idea> findIdeaById(Idea idea) throws IdeaNotFoundException {
        if (ideaHubRepository.existsById(idea.getIdeaId())) {
            return ideaHubRepository.findById(idea.getIdeaId());
        } else throw new IdeaNotFoundException("Entry not found");
    }

    //CODE FOR DISPLAYING IDEAS
    @Override
    public List<Idea> displayIdea(Idea idea) throws NullIdeaException {
        if (Objects.isNull(idea))
        { throw new NullIdeaException("No ideas found");}
        return ideaHubRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }
    // CODE FOR GETTING AN IDEA BY EMAIL ID
    @Override
    public List<Idea> getByEmailId(String emailId) throws IdeaNotFoundException {
        if(ideaHubRepository.findByEmailId(emailId)==null)
        {
            throw new IdeaNotFoundException("Entry not found");
        }
        return ideaHubRepository.findByEmailId(emailId);
    }


    // CODE FOR GETTING AN IDEA BY ITS TITLE
    @Override
    public Idea getByIdeaTitle(String title) throws IdeaNotFoundException {
        Idea recievedIdea = ideaHubRepository.findByTitle(title);
        if(recievedIdea==null){
            throw new IdeaNotFoundException("Idea not found");
        }
        else{
            return recievedIdea;
        }
    }


    // PRODUCING TO INTELLIGENT SERVICE USING RABBITMQ
    public void send(Idea idea) {
        rabbitTemplate.convertAndSend(ideaExchange, ideaRoutingkey, idea);
        log.info("Send msg = " + idea);
    }

    @Override
    public Idea updateApprovedServiceProvider(IdeaDto ideaDto){
        Idea fetchedIdea = ideaHubRepository.findByTitle(ideaDto.getTitle());
        List<ServiceProvider>serviceProviderList = fetchedIdea.getServiceProviders();
        if(serviceProviderList == null){
            serviceProviderList = new ArrayList<>();
        }
        else {
            serviceProviderList =fetchedIdea.getServiceProviders();
        }
        System.out.println(fetchedIdea);
        System.out.println(ideaDto.getAppliedServiceProviders().toString());

        for(int i=0;i<ideaDto.getAppliedServiceProviders().size();i++){

            serviceProviderList.add(ideaDto.getAppliedServiceProviders().get(i));
        }
        fetchedIdea.setServiceProviders(serviceProviderList);
        return ideaHubRepository.save(fetchedIdea);
    }

}

package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.domain.IdeaDTO;
import com.stackroute.recommendationservice.model.Idea;
import com.stackroute.recommendationservice.repository.IdeaRepository;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class IdeaServiceImplementation implements IdeaService {

    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Idea idea;
    @Autowired
    UserRepository userRepository;


    @Override
    public Collection<Idea> getAll() {
        return ideaRepository.getAllIdeas();
    }

    @Override
    public Idea saved(Idea idea) {
        Idea savedIdea = ideaRepository.save(idea);
        System.out.println("idea node "+savedIdea);

        ideaRepository.matchSubDomain(savedIdea.getSubDomain(),savedIdea.getIdeaName());
        return  savedIdea;

    }

    @Override
    public Idea matchSubDomain(String subDomainName,String ideaName) {
        return ideaRepository.matchSubDomain(subDomainName,ideaName);
    }

    @Override
    public List<Idea> getIdea( String role) {
        return ideaRepository.ideaRoleRelationship(role);

    }


    @RabbitListener(queues = "${javainuse.idea.queue}")
    public void recievedMessage(IdeaDTO ideaDTO) {
            Idea newIdea = new Idea();
        log.info("Received Message: " + ideaDTO);

        //this ideaDTO will have the properties need to set them and assign it to our domain objects to call the methods

        newIdea.setIdeaName(ideaDTO.getTitle());
        newIdea.setRole(ideaDTO.getRoles());
        newIdea.setSubDomain(ideaDTO.getSubDomain());
        System.out.println(ideaDTO.toString());
        ideaRepository.save(newIdea);
        log.info("ideaNode3 is created");

        ideaRepository.matchSubDomain(ideaDTO.getSubDomain(),ideaDTO.getTitle());
        log.info("relationship created");

        List<String> roleList= new ArrayList<>();
        roleList = ideaDTO.getRoles();
        for(int i=0;i<roleList.size();i++) {
           ideaRepository.ideaRoleRelationship(roleList.get(i));
        }
        log.info("relationship created");

    }

    @Override
    public List<Idea> getRecommendedIdeas(String emailId) {
       String role= userRepository.getRoleForUser(emailId);
       return ideaRepository.ideaRoleRelationship(role);
    }
}

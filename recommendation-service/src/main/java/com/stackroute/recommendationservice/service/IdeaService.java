package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Idea;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface IdeaService {

     Collection<Idea> getAll();

     Idea saved(Idea idea);

     Idea matchSubDomain(String subDomainName,String ideaName);

     List<Idea> getIdea(String role);

     List<Idea> getRecommendedIdeas(String emailId);
}

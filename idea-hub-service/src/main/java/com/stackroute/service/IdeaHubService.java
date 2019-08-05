package com.stackroute.service;
import java.util.List;
import java.util.Optional;

import com.stackroute.domain.Idea;
import com.stackroute.domain.ServiceProvider;
import com.stackroute.dto.IdeaDto;
import com.stackroute.exceptions.IdeaNotFoundException;
import com.stackroute.exceptions.NullIdeaException;


public interface IdeaHubService
{
        Idea addIdea(Idea idea);
        void deleteIdea(String ideaId) throws IdeaNotFoundException;
        public Idea updateIdea(String title, List<ServiceProvider> serviceproviders) throws IdeaNotFoundException;
        List<Idea> displayIdea(Idea idea)  throws NullIdeaException;
        void send(Idea idea);
        Optional<Idea> findIdeaById(Idea idea) throws IdeaNotFoundException;
        public List<Idea> getByEmailId(String emailId) throws IdeaNotFoundException;
        Idea getByIdeaTitle(String title)throws IdeaNotFoundException;
        public Idea updateApprovedServiceProvider(IdeaDto ideaDto);


}


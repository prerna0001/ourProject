package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserServices {


    Collection<User> getAll();

    String getByName(String name);

    Collection<User> getAllUsersBy(String subDomainName);

    User saved(User user);

    User matchUserSubDomain(String subDomainName, String name);


    User matchIdea(String ideaName, String name);
}

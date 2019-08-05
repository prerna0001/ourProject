package com.stackroute.recommendationservice.service;


import com.stackroute.recommendationservice.domain.UserDTO;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;


@Slf4j
@Service
public class UserServiceImplementation implements UserServices {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private  User user;



    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Collection<User> getAll() {
        return userRepository.getAllUsers();
    }


    @Override
    public String getByName(String name) {
        return userRepository.getNode(name);
    }

    @Override
    public Collection<User> getAllUsersBy(String subDomain) {
        return userRepository.getAllUserDomain(subDomain);
    }

    @Override
    public User saved(User user) {

        return userRepository.save(user);

    }

    @Override
    public User matchUserSubDomain(String subDomainName, String name) {
        return userRepository.matchUserSubDomain(subDomainName,name);
    }

    @Override
    public User matchIdea(String ideaName, String name) {
        return userRepository.matchUseridea(ideaName,name);
    }


    @RabbitListener(queues = "${serviceNeo4j.queue}")
    public void recievedMessage(UserDTO userDTO) {
        User newUser = new User();
        log.info("Received Message: " + userDTO);

        /*
         * this userprofileDTO will have the properties need to set them and assign it to
         * our domain objects to call the methods
         * */

        newUser.setEmailId(userDTO.getEmailId());
        newUser.setName(userDTO.getName());
        newUser.setSubDomain(userDTO.getSubDomain());
        newUser.setRole(userDTO.getRole());
        userRepository.save(newUser);
        System.out.println(userDTO.toString());
        log.info("userNode3 is created");
        ArrayList<String> domainList= new ArrayList<>();
        domainList = userDTO.getSubDomain();

        for(int i=0;i<domainList.size();i++) {
            userRepository.matchUserSubDomain(domainList.get(i), userDTO.getName());
        }
        log.info("relationship created");

    }


}

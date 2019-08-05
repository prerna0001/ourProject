package com.stackroute.userloginservice.service;

import com.stackroute.userloginservice.domain.User;
import com.stackroute.userloginservice.repository.UserRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByEmailIdAndPassword(String emailId, String password) {
        return userRepository.findByEmailIdAndPassword(emailId, password);
    }

    @RabbitListener(queues = "${innovator.queue}")
    public void recievedMessage(User user) {
        userRepository.save(user);
        System.out.println("Recieved Message From innovator:" + user.toString());

    }

    //For service provider producer
    @RabbitListener(queues = "${serviceProvider.queue}")
    public void recieveMessage(User user) {
        userRepository.save(user);
        System.out.println("Recieved Message From service-provider:" + user.toString());

    }

}
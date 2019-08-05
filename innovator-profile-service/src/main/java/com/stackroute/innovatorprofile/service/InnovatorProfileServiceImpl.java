package com.stackroute.innovatorprofile.service;

import com.stackroute.innovatorprofile.domain.InnovatorProfile;
import com.stackroute.innovatorprofile.exception.EmailIdAlreadyExistsException;
import com.stackroute.innovatorprofile.exception.EmailIdNotFoundException;
import com.stackroute.innovatorprofile.repository.InnovatorProfileRespository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InnovatorProfileServiceImpl implements InnovatorProfileService {
   private  InnovatorProfileRespository innovatorProfileRespository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${innovator.exchange}")
    String innovatorExchange;

    @Value("${innovator.routingkey}")
    private String innovatorRoutingKey;

    @Autowired
    public InnovatorProfileServiceImpl(InnovatorProfileRespository innovatorProfileRespository) {
        this.innovatorProfileRespository = innovatorProfileRespository;
    }

    //service implementation to save an innovator profile
    @Override
    public InnovatorProfile saveInnovatorProfile(InnovatorProfile innovatorProfile) throws EmailIdAlreadyExistsException {
        if (innovatorProfileRespository.existsById(innovatorProfile.getEmailId())) {
            throw new EmailIdAlreadyExistsException("EmailId Already Exists");
        }
        InnovatorProfile savedInnovatorProfile = innovatorProfileRespository.save(innovatorProfile);
        if (savedInnovatorProfile == null) {
            throw new EmailIdAlreadyExistsException("EmailId Already Exists");
        }

       else return savedInnovatorProfile;
    }

    //service implementation to get all innovator profiles
    @Override
    public List<InnovatorProfile> getInnovatorProfile() {
        return innovatorProfileRespository.findAll();
    }

    @Override
    public InnovatorProfile getByEmailId(String emailId) throws EmailIdNotFoundException {
        InnovatorProfile innovatorProfile= innovatorProfileRespository.findByEmailId(emailId);
        if(innovatorProfile==null)
        {
            throw new EmailIdNotFoundException("document not found by this emailId!!!");
        }
        else
            return innovatorProfile;
    }
    //service implementation to send all innovatorprofile through rabbitmq

    public void send(InnovatorProfile innovatorProfile) {
        rabbitTemplate.convertAndSend(innovatorExchange, innovatorRoutingKey, innovatorProfile);
        System.out.println("Send msg = " + innovatorProfile);
    }

}

package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.SubDomain;
import com.stackroute.recommendationservice.repository.SubDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class SubDomainServiceImpl implements SubDomainService {

    @Autowired
    SubDomainRepository subDomainRepository;


    @Override
    public Collection<SubDomain> getAll() {
        return subDomainRepository.getAllNode();
    }

    @Override
    public SubDomain saved(SubDomain subDomain) {
        return subDomainRepository.save(subDomain);
    }





}

package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.SubDomain;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface SubDomainService {
    Collection<SubDomain> getAll();

    SubDomain saved(SubDomain subDomain);
}

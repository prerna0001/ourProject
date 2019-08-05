package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.SubDomain;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubDomainRepository extends Neo4jRepository<SubDomain,Long> {

    //To get list of subDomain
    @Query("MATCH (u:SubDomain) RETURN u")
    Collection<SubDomain> getAllNode();

}

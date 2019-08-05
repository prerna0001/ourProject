package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Idea;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;

public interface IdeaRepository extends Neo4jRepository<Idea,Long> {

    //query to get all ideas
    @Query("MATCH (u:Idea) RETURN u")
    Collection<Idea> getAllIdeas();

    //Query to create relationship between idea and subDomain
    @Query("MATCH (n:Idea),(s:SubDomain) WHERE s.subDomainName={subDomainName}  AND n.ideaName={ideaName} CREATE (n)-[r:is_of]->(s)RETURN r")
    Idea matchSubDomain(@Param("subDomainName") String subDomainName,@Param("ideaName") String ideaName);


    //Query to retrieve ideas having specific role
    @Query("MATCH (n:Idea) \n" +
            "WHERE any(x IN n.role WHERE x ={role})\n" +
            "RETURN n")
    List<Idea> ideaRoleRelationship(@Param("role")String role);

}

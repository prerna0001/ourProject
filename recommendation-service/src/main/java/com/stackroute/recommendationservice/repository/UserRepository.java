package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface UserRepository extends Neo4jRepository<User,Long> {

    @Query("MATCH (u:User) RETURN u")
    Collection<User> getAllUsers();

    //Query to retrieve role of a user from name
    @Query("MATCH (u:User) WHERE u.name={userName} RETURN u.role")
    public String getNode(@Param("userName") String userName);

    ////Query to retrieve role of a user from emailId
    @Query("MATCH (u:User) WHERE u.emailId={emailId} RETURN u.role")
    public String getRoleForUser(@Param("emailId") String emailId);

    @Query("MATCH (u:User) WHERE u.subDomain={subDomain} RETURN u")
    Collection<User> getAllUserDomain(@Param("subDomain")String subDomain);

    //Query to create relationship between user and subDomain
    @Query("MATCH (n:User),(s:SubDomain) WHERE s.subDomainName={subDomainName}  AND n.name={name} CREATE (n)-[r:work_on]->(s)RETURN r")
    User matchUserSubDomain(@Param("subDomainName") String subDomainName,@Param("name") String name);


    //Query to create relationship between user and idea
    @Query("MATCH (n:User),(s:Idea) WHERE s.ideaName={ideaName}  AND n.name={name} CREATE (n)-[r:work_upon]->(s)RETURN r")
    User matchUseridea(@Param("ideaName") String ideaName,@Param("name") String name);
}

package com.stackroute.recommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
@Component
public class User {

    @GraphId
    private Long id;
    private String emailId;
    private String name;

    private ArrayList<String> subDomain;
    private String role;

    @Relationship(type = "work_on", direction = Relationship.OUTGOING)
    private SubDomain subDomainRel;

    @Relationship(type = "work_upon", direction = Relationship.OUTGOING)
    private Idea ideaRel;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", subDomain=" + subDomain +
                ", role='" + role + '\'' +
                ", subDomainRel=" + subDomainRel +
                ", ideaRel=" + ideaRel +
                '}';
    }
}

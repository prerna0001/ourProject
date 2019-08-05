package com.stackroute.recommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
@Component
public class Idea {
    @Id @GeneratedValue
    private Long id;
    private String ideaName;
    private String description;
    private String domain;
    private List<String> role;
    private String subDomain;
    private double budget;
    private Date timestamp;
    @Relationship(type = "is_of", direction = Relationship.OUTGOING)
    private SubDomain subDomainRel;



    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", ideaName='" + ideaName + '\'' +
                ", description='" + description + '\'' +
                ", domain='" + domain + '\'' +
                ", role=" + role +
                ", subDomain='" + subDomain + '\'' +
                ", budget=" + budget +
                ", timestamp=" + timestamp +
                ", subDomainRel=" + subDomainRel +
                '}';
    }
}

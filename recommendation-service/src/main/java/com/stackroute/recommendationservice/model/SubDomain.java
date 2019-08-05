package com.stackroute.recommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.springframework.stereotype.Component;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
@Component
public class SubDomain {
    @Id
    private Long id;
    private String subDomainName;


    @Override
    public String toString() {
        return "SubDomain{" +
                "id=" + id +
                ", subDomainName='" + subDomainName + '\'' +
                '}';
    }
}




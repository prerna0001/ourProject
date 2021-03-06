package com.stackroute.dto;

import com.stackroute.domain.ServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class IdeaDto {
    @Id
    private String ideaId;
    private String emailId;
    private String title;
    private String description;
    private String domain;
    private  String subDomain;
    private double budget;
    private Date timestamp;
    private List<String> appliedServiceProviderEmail = new ArrayList<>();
    private List<String> roles;
    private List<ServiceProvider> appliedServiceProviders = new ArrayList<>();

    @Override
    public String toString() {
        return "IdeaDto{" +
                "ideaId='" + ideaId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", domain='" + domain + '\'' +
                ", subDomain='" + subDomain + '\'' +
                ", budget=" + budget +
                ", timestamp=" + timestamp +
                ", appliedServiceProviderEmail=" + appliedServiceProviderEmail +
                ", roles=" + roles +
                ", appliedServiceProviders=" + appliedServiceProviders +
                '}';
    }
}
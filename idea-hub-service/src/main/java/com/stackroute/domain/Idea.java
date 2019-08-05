package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Idea {
  @Id
  private String ideaId;
  private String emailId;
  private String title;
  private String description;
  private String domain;
  private  String subDomain;
  private double budget;
  private Date timestamp;
  private List<String> roles;
  private List<ServiceProvider> serviceProviders;

  @Override
  public String toString() {
    return "Idea{" +
            "ideaId='" + ideaId + '\'' +
            ", emailId='" + emailId + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", domain='" + domain + '\'' +
            ", subDomain='" + subDomain + '\'' +
            ", budget=" + budget +
            ", timestamp=" + timestamp +
            ", roles=" + roles +
            ", serviceProviders=" + serviceProviders +
            '}';
  }

  public String getIdeaId() {
    return ideaId;
  }

  public void setIdeaId(String ideaId) {
    this.ideaId = ideaId;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getSubDomain() {
    return subDomain;
  }

  public void setSubDomain(String subDomain) {
    this.subDomain = subDomain;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public List<ServiceProvider> getServiceProviders() {
    return serviceProviders;
  }

  public void setServiceProviders(List<ServiceProvider> serviceProviders) {
    this.serviceProviders = serviceProviders;
  }
}

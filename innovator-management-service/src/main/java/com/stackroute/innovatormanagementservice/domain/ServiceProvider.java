package com.stackroute.innovatormanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ServiceProvider {
    @Id
    private String emailId;
    private String password;
    String name;
    String domain;
    List<String> subDomain;
    String role;
    String about;
    ArrayList<String> skills;
    String experience;
    Double chargePerHour;
    boolean status;

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", subDomain=" + subDomain +
                ", role='" + role + '\'' +
                ", about='" + about + '\'' +
                ", skills=" + skills +
                ", experience='" + experience + '\'' +
                ", chargePerHour=" + chargePerHour +
                ", status='" + status + '\'' +
                '}';
    }
}
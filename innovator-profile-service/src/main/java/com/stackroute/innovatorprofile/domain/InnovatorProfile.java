package com.stackroute.innovatorprofile.domain;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InnovatorProfile {
    @Id
    private String emailId;
    private String password;
    String name;
    String domain;
    List<String> subDomain;

    @Override
    public String toString() {
        return "InnovatorProfile{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", subDomain=" + subDomain +
                '}';
    }
}

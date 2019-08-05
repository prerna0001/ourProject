package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ServiceProvider {
    @Id
    private String emailId;
    private String password;
    String name;
    String domain;
    List<String> subDomain;
    String role;
    ArrayList<String> skills;
    String about;
    String experience;
    Double chargePerHour;


}

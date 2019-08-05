package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {


    private String emailId;
    private String password;
    String name;
    String domain;
    ArrayList<String> subDomain;
    String role;
    ArrayList<String> skills;
    String about;
    String experience;
    Double chargePerHour;
}
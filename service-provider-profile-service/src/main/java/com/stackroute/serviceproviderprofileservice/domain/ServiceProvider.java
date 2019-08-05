package com.stackroute.serviceproviderprofileservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;


@Document(collection = "service")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceProvider {
     @Id
     private String emailId;
     private String password;
     String name;
     String domain;
     List<String> subDomain;
     String role;
     List<String> skills;
     String about;
     BigDecimal chargePerHour;

}

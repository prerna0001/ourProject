package com.stackroute.recommendationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaDTO {
    private String ideaId;
    private String title;
    private String description;
    private String domain;
    private  String subDomain;
    private double budget;
    private Date timestamp;
    private List<String> roles;

}

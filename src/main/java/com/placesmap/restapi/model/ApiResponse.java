package com.placesmap.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponse {

    private List<String> html_attributions;
    private String next_page_token;
    private List<Place> results;
    private String status;
    
}

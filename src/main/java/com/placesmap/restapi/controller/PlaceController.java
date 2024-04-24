package com.placesmap.restapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/place")
public class PlaceController {

    @Value("${googleApiKey}")
    private String googleApiKey;

    private final String apiUrl = "https://maps.googleapis.com";
    private final RestTemplate restTemplate;

    public PlaceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/nearbysearch")
    public ResponseEntity<String> nearBySearch(@RequestParam Float lat, @RequestParam Float lng, @RequestParam Float radius) {
        String endpoint = "/maps/api/place/nearbysearch/json";
        String callURL = apiUrl + endpoint + 
            "?location=" + lat.toString() + "," + lng.toString() + 
            "&radius=" + radius.toString() + 
            "&key=" + googleApiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(callURL, String.class);
        return response;
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> photo(@RequestParam String photoreference, @RequestParam Integer maxheight, @RequestParam Integer maxwidth) {
        String endpoint = "/maps/api/place/photo";
        String callURL = apiUrl + endpoint + 
            "?photoreference=" + photoreference + 
            "&maxheight=" + maxheight.toString() + 
            "&maxwidth=" + maxheight.toString();
        ResponseEntity<byte[]> response = restTemplate.getForEntity(callURL, byte[].class);
        return response;
    }
}

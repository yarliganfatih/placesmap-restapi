package com.placesmap.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.placesmap.restapi.model.SearchedArea;
import com.placesmap.restapi.repository.SearchedAreaRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    @Value("${googleApiKey}")
    private String googleApiKey;

    private final String apiUrl = "https://maps.googleapis.com";
    private final RestTemplate restTemplate;
    private final SearchedAreaRepository searchedAreaRepository;

    public PlaceController(RestTemplate restTemplate, SearchedAreaRepository searchedAreaRepository) {
        this.restTemplate = restTemplate;
        this.searchedAreaRepository = searchedAreaRepository;
    }

    @GetMapping(value="/nearbysearch", produces = "application/json")
    public ResponseEntity<String> nearBySearch(@RequestParam Float lat, @RequestParam Float lng, @RequestParam Float radius) {
        List<SearchedArea> searchedAreasBefore = searchedAreaRepository.searchedBefore(lat, lng, radius);
        if(searchedAreasBefore.isEmpty()) return nearBySearchFromAPI(lat, lng, radius);
        String responseBody = searchedAreasBefore.get(0).getResponseBody();
        return ResponseEntity.ok(responseBody);
    }

    public void saveForReuse(Float lat, Float lng, Float radius, String responseBody){
        SearchedArea area = new SearchedArea();
        area.setLat(lat);
        area.setLng(lng);
        area.setRadius(radius);
        area.setResponseBody(responseBody);
        searchedAreaRepository.save(area);
    }
        
    public ResponseEntity<String> nearBySearchFromAPI(Float lat, Float lng, Float radius) {
        String endpoint = "/maps/api/place/nearbysearch/json";
        String callURL = apiUrl + endpoint + 
            "?location=" + lat.toString() + "," + lng.toString() + 
            "&radius=" + radius.toString() + 
            "&key=" + googleApiKey;
        // TODO
        //ApiResponse response = restTemplate.getForObject(callURL, ApiResponse.class);
        //System.out.println(response.getResults().get(1).getGeometry().getLocation().getLat());
        ResponseEntity<String> response = restTemplate.getForEntity(callURL, String.class);
        saveForReuse(lat, lng, radius, response.getBody());
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

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
public class Place {

    private String place_id;
    private float rating;
    private int user_ratings_total;
    private int price_level;
    private List<String> types;
    private String vicinity;
    private List<PlacePhoto> photos;
    private Geometry geometry;

}

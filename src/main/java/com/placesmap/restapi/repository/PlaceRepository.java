package com.placesmap.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placesmap.restapi.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    
}

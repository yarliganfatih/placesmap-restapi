package com.placesmap.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.placesmap.restapi.model.SearchedArea;

@Repository
public interface SearchedAreaRepository extends JpaRepository<SearchedArea, Integer> {
    @Query(value = "SELECT * FROM searched_areas WHERE lat = :lat AND lng = :lng AND radius = :radius", nativeQuery = true)
    public List<SearchedArea> searchedBefore(@Param("lat") float lat, @Param("lng") float lng, @Param("radius") float radius);
}

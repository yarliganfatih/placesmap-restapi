package com.placesmap.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.placesmap.restapi.model.SearchedArea;

@Repository
public interface SearchedAreaRepository extends JpaRepository<SearchedArea, Integer> {
    @Query(
        nativeQuery = true,
        value = 
            "SELECT * " +
            "FROM searched_areas " +
            "WHERE " +
                "SQRT(POW(lat - :lat, 2) + POW(lng - :lng, 2)) " + // distance between two points
                " * 111000 " + // approximate length (m) of one degree
                " + :radius " + // the entire area of the circle
                "< " + // if it inside
                "radius + 0.1 " + // in search area
                " AND " +
                "ABS(radius - :radius) < 1000" // for scaling to minimize the margin of error
    )
    public List<SearchedArea> searchedBefore(@Param("lat") float lat, @Param("lng") float lng, @Param("radius") float radius);
}

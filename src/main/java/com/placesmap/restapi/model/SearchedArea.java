package com.placesmap.restapi.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "searched_areas")
public class SearchedArea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float lat;

    private float lng;

    private float radius;

    @Column(columnDefinition = "longtext")
    private String responseBody;

}

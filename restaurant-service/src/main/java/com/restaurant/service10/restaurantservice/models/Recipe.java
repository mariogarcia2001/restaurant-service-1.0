package com.restaurant.service10.restaurantservice.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME") 
    private String name;

    @Column(name = "INGREDIENTS") 
    private String ingredients;

    @Column(name = "PREPARE") 
    private String prepare;

    @Column(name = "OBSERVATION") 
    private String observationS;

    @ManyToOne
    @JoinColumn(name="RECIPE_ID", nullable=false)
    private Recipe recipe; 
}

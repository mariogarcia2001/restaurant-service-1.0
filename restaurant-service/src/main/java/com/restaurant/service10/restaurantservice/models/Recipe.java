package com.restaurant.service10.restaurantservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_RECIPES")
@Getter
@Setter
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
    private String observations;

    @ManyToOne
    @JoinColumn(name="ORDER_ID", nullable=false)
    private Order order; 

    @OneToMany(mappedBy = "recipe")
    private List<Food> foods;
}

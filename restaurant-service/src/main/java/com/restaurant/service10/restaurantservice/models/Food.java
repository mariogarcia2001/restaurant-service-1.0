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
@Table(name="TBL_FOODS")
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME") 
    private String name;

    @Column(name = "PRICE") 
    private Float price;

    @Column(name = "CATEGORY") 
    private String category;

    @ManyToOne
    @JoinColumn(name="FOOD_ID", nullable=false)
    private Food food;

    //

    @OneToMany(mappedBy="recipe")       
    private List<Recipe> recipes;
     
}

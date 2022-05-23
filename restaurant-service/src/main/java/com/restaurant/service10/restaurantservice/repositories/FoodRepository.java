package com.restaurant.service10.restaurantservice.repositories;

import com.restaurant.service10.restaurantservice.models.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    
}

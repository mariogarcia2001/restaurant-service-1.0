package com.restaurant.service10.restaurantservice.repositories;

import java.util.List;

import com.restaurant.service10.restaurantservice.models.Order;
import com.restaurant.service10.restaurantservice.models.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    public List<Recipe> findByOrder(Order order);
}

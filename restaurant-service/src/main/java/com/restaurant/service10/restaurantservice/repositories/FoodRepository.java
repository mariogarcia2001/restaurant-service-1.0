package com.restaurant.service10.restaurantservice.repositories;

import java.util.List;

import com.restaurant.service10.restaurantservice.models.Food;
import com.restaurant.service10.restaurantservice.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<Food,Long> {
    public List<Food> findByRecipe(Recipe recipe);
}

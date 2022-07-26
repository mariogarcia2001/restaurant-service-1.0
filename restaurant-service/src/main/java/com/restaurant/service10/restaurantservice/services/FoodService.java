package com.restaurant.service10.restaurantservice.services;

import java.util.List;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;

public interface FoodService {
    public List<FoodDTO> create(Long idOrder, Long idRecipe, List<NewFoodDTO> list);
    public List<FoodDTO> list(Long idOrder, Long idRecipe);
    public void remove(Long idOrder, Long idRecipe);
}

package com.restaurant.service10.restaurantservice.services;

import java.util.List;

import com.restaurant.service10.restaurantservice.dtos.RecipeDTO;
import com.restaurant.service10.restaurantservice.dtos.RecipeOrderDTO;
import com.restaurant.service10.restaurantservice.dtos.NewRecipeDTO;

public interface RecipeService {
    public RecipeDTO create(Long idOrder, NewRecipeDTO recipeDTO);
    public RecipeOrderDTO retrieve(Long idOrder, Long id);
    public RecipeOrderDTO update(RecipeDTO recipeDTO, Long idOrder, Long id);
    public void delete(Long idOrder, Long id);

    public List<RecipeDTO> list(Long idOrder);
}

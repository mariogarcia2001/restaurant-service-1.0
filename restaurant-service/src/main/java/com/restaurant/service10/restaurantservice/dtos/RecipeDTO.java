package com.restaurant.service10.restaurantservice.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO extends NewRecipeDTO{
    private Long id;
    private List<FoodDTO> foods;
}

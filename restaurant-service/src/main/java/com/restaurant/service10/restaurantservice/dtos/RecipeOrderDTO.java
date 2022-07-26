package com.restaurant.service10.restaurantservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeOrderDTO extends RecipeDTO {
    private OrderDTO order;
}

package com.restaurant.service10.restaurantservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewRecipeDTO {
    private String name;
    private String ingredients;
    private String prepare;
    private String observations;
}

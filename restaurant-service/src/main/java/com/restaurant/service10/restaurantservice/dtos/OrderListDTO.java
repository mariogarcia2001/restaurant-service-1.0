package com.restaurant.service10.restaurantservice.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderListDTO {
    private Long id;
    private String description;
    private String waiter;
    private String statusOfOrder;
}

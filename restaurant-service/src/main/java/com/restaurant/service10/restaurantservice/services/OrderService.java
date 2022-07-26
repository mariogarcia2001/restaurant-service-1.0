package com.restaurant.service10.restaurantservice.services;

import java.util.List;

import com.restaurant.service10.restaurantservice.dtos.OrderDTO;
import com.restaurant.service10.restaurantservice.dtos.OrderListDTO;
import com.restaurant.service10.restaurantservice.dtos.NewOrderDTO;

public interface OrderService {
    
    public OrderDTO create(NewOrderDTO orderDTO);
    public OrderDTO retrieve(Long id);
    public OrderDTO update(OrderDTO orderDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<OrderListDTO> list(int page, int size, String sort);
}

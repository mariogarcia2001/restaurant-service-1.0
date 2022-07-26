package com.restaurant.service10.restaurantservice.repositories;

import java.util.List;

import com.restaurant.service10.restaurantservice.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByStatusOfOrder(String statusOfOrder);
}

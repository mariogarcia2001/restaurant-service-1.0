package com.restaurant.service10.restaurantservice.services.impl;

import com.restaurant.service10.restaurantservice.dtos.NewOrderDTO;
import com.restaurant.service10.restaurantservice.dtos.OrderDTO;
import com.restaurant.service10.restaurantservice.dtos.OrderListDTO;
import com.restaurant.service10.restaurantservice.exceptions.NoContentException;
import com.restaurant.service10.restaurantservice.exceptions.ResourceNotFoundException;
import com.restaurant.service10.restaurantservice.models.Order;
import com.restaurant.service10.restaurantservice.repositories.OrderRepository;
import com.restaurant.service10.restaurantservice.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    final ModelMapper modelMapper;
    final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository repository, ModelMapper mapper){
        this.orderRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public OrderDTO create(NewOrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderRepository.save(order);        
        return modelMapper.map(order, OrderDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO retrieve(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO orderDTO, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found"));        
              
        Order orderUpdated = modelMapper.map(orderDTO, Order.class);
        //Keeping values
        orderUpdated.setCreatedBy(order.getCreatedBy());
        orderUpdated.setCreatedDate(order.getCreatedDate());
        orderRepository.save(orderUpdated);   
        return modelMapper.map(orderUpdated, OrderDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found"));        
        orderRepository.deleteById(order.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Order> orders = orderRepository.findAll(pageable);
        if(orders.isEmpty()) throw new NoContentException("Orders is empty");
        return orders.stream().map(order -> modelMapper.map(order, OrderListDTO.class))
            .collect(Collectors.toList());        
    }

    @Override
    public long count() {        
        return orderRepository.count();
    }
    
}



package com.restaurant.service10.restaurantservice.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;
import com.restaurant.service10.restaurantservice.exceptions.NoContentException;
import com.restaurant.service10.restaurantservice.exceptions.ResourceNotFoundException;
import com.restaurant.service10.restaurantservice.models.Food;
import com.restaurant.service10.restaurantservice.models.Order;
import com.restaurant.service10.restaurantservice.models.Recipe;
import com.restaurant.service10.restaurantservice.repositories.FoodRepository;
import com.restaurant.service10.restaurantservice.repositories.OrderRepository;
import com.restaurant.service10.restaurantservice.repositories.RecipeRepository;
import com.restaurant.service10.restaurantservice.services.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
    final ModelMapper modelMapper;
    final FoodRepository repository;
    final RecipeRepository recipeRepository;
    final OrderRepository orderRepository;

    public FoodServiceImpl(FoodRepository r, RecipeRepository qr, OrderRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.orderRepository = er;
        this.recipeRepository = qr;
    }

    @Override
    @Transactional
    public List<FoodDTO> create(Long idOrder, Long idRecipe, List<NewFoodDTO> foods) {
        Order order = orderRepository.findById(idOrder).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe.setOrder(order);
        List<FoodDTO> result = new ArrayList<FoodDTO>();
        for(NewFoodDTO op : foods){
            Food food = modelMapper.map(op, Food.class);
            food.setRecipe(recipe);
            repository.save(food);
            result.add(modelMapper.map(food, FoodDTO.class));
        }
        /*foods.forEach(op -> {
        });        */
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodDTO> list(Long idOrder, Long idRecipe) {
        Order order = orderRepository.findById(idOrder).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe.setOrder(order);
        if(recipe.getFoods().isEmpty()) throw new NoContentException("Foods is empty");
        return recipe.getFoods().stream().map(op -> modelMapper.map(op, FoodDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long idOrder, Long idRecipe) {
        Order order = orderRepository.findById(idOrder).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe.setOrder(order);
        if(recipe.getFoods().isEmpty()) throw new NoContentException("Foods is empty");
        recipe.getFoods().forEach(repository::delete);                      
    }


}

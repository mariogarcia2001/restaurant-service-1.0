package com.restaurant.service10.restaurantservice.services.impl;

import com.restaurant.service10.restaurantservice.dtos.NewRecipeDTO;
import com.restaurant.service10.restaurantservice.dtos.RecipeDTO;
import com.restaurant.service10.restaurantservice.dtos.RecipeOrderDTO;
import com.restaurant.service10.restaurantservice.exceptions.NoContentException;
import com.restaurant.service10.restaurantservice.exceptions.ResourceNotFoundException;
import com.restaurant.service10.restaurantservice.models.Order;
import com.restaurant.service10.restaurantservice.models.Recipe;
import com.restaurant.service10.restaurantservice.repositories.OrderRepository;
import com.restaurant.service10.restaurantservice.repositories.RecipeRepository;
import com.restaurant.service10.restaurantservice.services.RecipeService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService{
    final ModelMapper modelMapper;
    final RecipeRepository repository;
    final OrderRepository orderRepository;

    public RecipeServiceImpl(RecipeRepository r, OrderRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.orderRepository = er;
    }

    @Override
    @Transactional
    public RecipeDTO create(Long idOrder, NewRecipeDTO recipeDTO) {
        Order order = orderRepository.findById(idOrder)
            .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);    
        recipe.setOrder(order);
        repository.save(recipe);
        return modelMapper.map(recipe, RecipeDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public RecipeOrderDTO retrieve(Long idOrder, Long id) {
        Order order = orderRepository.findById(idOrder)
            .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe.setOrder(order);
        return modelMapper.map(recipe, RecipeOrderDTO.class);
    }

    @Override
    @Transactional
    public RecipeOrderDTO update(RecipeDTO recipeDTO, Long idOrder, Long id) {
        Order order = orderRepository.findById(idOrder)
        .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe = modelMapper.map(recipeDTO, Recipe.class);
        recipe.setOrder(order);
        repository.save(recipe);       
        return modelMapper.map(recipe, RecipeOrderDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long idOrder, Long id) {
        Order order = orderRepository.findById(idOrder)
        .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        Recipe recipe = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recipe not found"));
        recipe.setOrder(order);
        repository.deleteById(recipe.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<RecipeDTO> list(Long idOrder) {
        Order order = orderRepository.findById(idOrder)
            .orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        List<Recipe> recipes = repository.findByOrder(order);
        if(recipes.isEmpty()) throw new NoContentException("Recipes is empty");
        //Lambda ->
        return recipes.stream().map(q -> modelMapper.map(q, RecipeDTO.class) )
            .collect(Collectors.toList());
    }

}

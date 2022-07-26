package com.restaurant.service10.restaurantservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.service10.restaurantservice.dtos.FoodDTO;
import com.restaurant.service10.restaurantservice.dtos.NewFoodDTO;
import com.restaurant.service10.restaurantservice.services.FoodService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class FoodController {
    
    final FoodService service;

    public FoodController(FoodService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/recipes/{idRecipe}/foods")
    public ResponseEntity<List<FoodDTO>> create(@PathVariable("id") Long id, @PathVariable("idRecipe") Long idRecipe, @Valid @RequestBody List<NewFoodDTO> foodsDTO){
        List<FoodDTO> foodDTOs = service.create(id, idRecipe, foodsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodDTOs);        
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}/recipes/{idRecipe}/foods")
    public ResponseEntity<List<FoodDTO>> delete(@PathVariable("id") Long id, @PathVariable("idRecipe") Long idRecipe){
        service.remove(id, idRecipe);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/recipes/{idRecipe}/foods")
    public ResponseEntity<List<FoodDTO>> list(@PathVariable("id") Long id, @PathVariable("idRecipe") Long idRecipe){
        List<FoodDTO> foodDTOs = service.list(id, idRecipe);
        return ResponseEntity.status(HttpStatus.OK).body(foodDTOs);        
    }

}




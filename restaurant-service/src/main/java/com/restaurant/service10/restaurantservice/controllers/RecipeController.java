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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.service10.restaurantservice.dtos.NewRecipeDTO;
import com.restaurant.service10.restaurantservice.dtos.RecipeDTO;
import com.restaurant.service10.restaurantservice.dtos.RecipeOrderDTO;
import com.restaurant.service10.restaurantservice.services.RecipeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class RecipeController {
    
    final RecipeService service;

    public RecipeController(RecipeService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/recipes")
    public ResponseEntity<RecipeDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewRecipeDTO recipeDTO){
        RecipeDTO questioDTO = service.create(id, recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(questioDTO);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{idOrder}/recipes/{id}")
    public ResponseEntity<RecipeOrderDTO> retrive(@PathVariable("idOrder") Long idOrder, @PathVariable("id") Long id){
        RecipeOrderDTO result = service.retrieve(idOrder, id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idOrder}/recipes/{id}")
    public ResponseEntity<RecipeOrderDTO> update(@RequestBody RecipeDTO recipeDTO, @PathVariable("idOrder") Long idOrder, @PathVariable("id") Long id){
        RecipeOrderDTO result = service.update(recipeDTO, idOrder, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idOrder}/recipes/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idOrder") Long idOrder, @PathVariable("id") Long id){
        service.delete(idOrder, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/recipes")
    public ResponseEntity<List<RecipeDTO>> list(@PathVariable("id") Long id){
        List<RecipeDTO> recipes = service.list(id);
        return ResponseEntity.ok().body(recipes);        
    }

}




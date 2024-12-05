package com.msl.ExpenseTracker.controller;

import com.msl.ExpenseTracker.model.Categories;
import com.msl.ExpenseTracker.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eta")
public class CategoryController{

    @Autowired
    CategoriesService service;

    @GetMapping("/categories")
    public List<Categories> getCategories(){
        return service.getCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Categories> getCategoriesById(@PathVariable int  id){
        Categories category = service.getCategoriesById(id);
        if(category!=null){
            return new ResponseEntity<>(service.getCategoriesById(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/categories")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories categories){
        if(categories!=null){
            return  new ResponseEntity<>(service.addCategory(categories),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

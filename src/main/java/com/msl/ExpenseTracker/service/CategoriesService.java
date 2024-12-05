package com.msl.ExpenseTracker.service;

import com.msl.ExpenseTracker.model.Categories;
import com.msl.ExpenseTracker.repo.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriesService {
    @Autowired
    CategoriesRepo repo;
    public List<Categories> getCategories() {
        return repo.findAll();
    }
    public Categories getCategoriesById(int id){
        return repo.findById(id).orElse(null);
    }

    public Categories addCategory(Categories categories) {
        return repo.save(categories);
    }
}

package com.godeltech.edushop.controller;

import com.godeltech.edushop.model.Category;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by d.ihnatovich on 10/13/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Category> findAll() {
        return categoryService.getAll();
    }
}

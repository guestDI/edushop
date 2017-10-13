package com.godeltech.edushop.service;

import com.godeltech.edushop.model.Category;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by d.ihnatovich on 10/13/2017.
 */
@Service
@Transactional
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }
}

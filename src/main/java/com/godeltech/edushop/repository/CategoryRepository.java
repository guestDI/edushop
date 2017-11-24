package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Category;
import com.godeltech.edushop.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by d.ihnatovich on 10/12/2017.
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);
}

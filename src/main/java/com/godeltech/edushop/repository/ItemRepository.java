package com.godeltech.edushop.repository;

import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmitry on 08.10.2017.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
    List<Item> findItemsByFilter(@Param("filter") ItemFilter itemFilter);
}

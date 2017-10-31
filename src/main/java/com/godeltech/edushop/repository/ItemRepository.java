package com.godeltech.edushop.repository;

import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.model.User;
import org.springframework.data.domain.Pageable;
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
//    @Query(nativeQuery = true,
//            value =
//            "SELECT i.* FROM public.item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
//            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
//            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
//            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
//            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
//            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
//            "AND (:#{#filter.categoryId} IS NULL OR i.category_id = :#{#filter.categoryId}) ")
//    List<Item> findItemsByFilter(@Param("filter") ItemFilter itemFilter);
    @Query("SELECT i FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
    List<Item> findItemsByFilter(@Param("filter") ItemFilter itemFilter);

    @Query("SELECT COUNT(*) FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
    Long findItemsCountByFilter(@Param("filter") ItemFilter itemFilter);

    @Query("SELECT i FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id IN (SELECT c.id FROM Category c WHERE c.parentCategory.id = :#{#filter.categoryId} OR c.id = :#{#filter.categoryId}))" )
    List<Item> findItemsByFilterAndPage(@Param("filter") ItemFilter itemFilter, Pageable pageable);

    @Query("SELECT count(*) from Item i where i.supplier.id = :id")
    Long getItemsCount(@Param("id") Long supplierId);

//    @Query("SELECT i FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
//            "AND (:description IS NULL OR i.description LIKE '%':description) " +
//            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
//            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
//            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
//            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
//            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
//    List<Item> findItemsByFilter(@Param("manufacture") String manufacture, );
}

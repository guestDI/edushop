package com.godeltech.edushop.repository;

import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.model.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.active = true AND ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.inStore} IS NULL OR i.quantity > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
    List<Item> findItemsByFilter(@Param("filter") ItemFilter itemFilter);

    @Query("SELECT COUNT(*) FROM Item i WHERE i.active = true AND ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.inStore} IS NULL OR i.quantity > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ")
    Long findItemsCountByFilter(@Param("filter") ItemFilter itemFilter);

    @Query("SELECT i FROM Item i WHERE i.active = true AND ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
            "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
            "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
            "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
            "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
            "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
            "AND (:#{#filter.inStore} IS NULL OR i.quantity > 0) " +
            "AND (:#{#filter.categoryId} IS NULL OR i.category.id IN (SELECT c.id FROM Category c WHERE c.parentCategory.id = :#{#filter.categoryId} OR c.id = :#{#filter.categoryId}))" )
    List<Item> findItemsByFilterAndPage(@Param("filter") ItemFilter itemFilter, Pageable pageable);

    @Query("SELECT count(*) from Item i where i.supplier.id = :id")
    Long getItemsCount(@Param("id") Long supplierId);

    @Query("SELECT count(*) from Item i WHERE i.active = true")
    Long getCountOfAllItems();

    @Query("SELECT count(*) from Item i WHERE i.discount > 0")
    Long getCountOfAllItemsWithDiscount();

    @Query("SELECT count(*) from Item i WHERE i.quantity = 0 AND i.active = true AND i.supplier.id = :id")
    Long getCountOfAllItemsWithZeroQuantity(@Param("id") Long supplierId);

    @Query("SELECT count(*) from Item i WHERE i.active = false AND i.supplier.id = :id")
    Long getCountOfAllDisabledItems(@Param("id") Long supplierId);


    @Modifying
    @Query("UPDATE Item i SET i.category.id = :#{#dto.category.id}, i.manufacturer = :#{#dto.manufacturer}, i.name = :#{#dto.name}, " +
            "i.description = :#{#dto.description}, i.quantity = :#{#dto.quantity}, i.price = :#{#dto.price}, i.discount = :#{#dto.discount}, i.active = :#{#dto.active}" +
            " WHERE i.id = :#{#dto.id}")
    int update(@Param("dto") ItemDTO dto);

    @Modifying(clearAutomatically = true)
    @Query("Update Item i SET i.active = :status WHERE i.id = :id")
    Integer deleteItemFromShelf(@Param("id") Long id, @Param("status") boolean status);

}

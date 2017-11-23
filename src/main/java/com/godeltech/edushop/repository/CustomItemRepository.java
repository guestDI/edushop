package com.godeltech.edushop.repository;

import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.dto.ItemFilterWithPaging;
import com.godeltech.edushop.dto.ItemSupplierDto;
import com.godeltech.edushop.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by d.ihnatovich on 10/19/2017.
 */
@Repository
public class CustomItemRepository {
    @Autowired
    private EntityManager entityManager;

    public List<Item> findItemsForPage(ItemSupplierDto itemSupplierDto) {
        Query query = entityManager.createQuery("SELECT i FROM Item i WHERE i.supplier.id=:id");
        query.setParameter("id", itemSupplierDto.getSupplierId());

        PageRequest pageRequest = new PageRequest(itemSupplierDto.getPage(), itemSupplierDto.getCount());
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        return query.getResultList();
    }

    public List<Item> findZeroItemsForPage(ItemSupplierDto itemSupplierDto) {
        Query query = entityManager.createQuery("SELECT i FROM Item i WHERE i.supplier.id=:id AND i.quantity = 0");
        query.setParameter("id", itemSupplierDto.getSupplierId());

        PageRequest pageRequest = new PageRequest(itemSupplierDto.getPage(), itemSupplierDto.getCount());
        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        return query.getResultList();
    }

//    public List<Item> findItemsByFilterForPage(ItemFilterWithPaging itemFilterWithPaging) {
//        "SELECT i FROM Item i WHERE ( :#{#filter.manufacturer} IS NULL OR i.manufacturer = :#{#filter.manufacturer}) " +
//                "AND (:#{#filter.description} IS NULL OR i.description LIKE :#{#filter.description}) " +
//                "AND (:#{#filter.name} IS NULL OR i.name LIKE :#{#filter.name}) " +
//                "AND (:#{#filter.minPrice} IS NULL OR i.price >= :#{#filter.minPrice}) " +
//                "AND (:#{#filter.maxPrice} IS NULL OR i.price <= :#{#filter.maxPrice}) " +
//                "AND (:#{#filter.discount} IS NULL OR i.discount > 0) " +
//                "AND (:#{#filter.categoryId} IS NULL OR i.category.id = :#{#filter.categoryId}) ";
//        Query query = entityManager.createQuery("");
//
//        query.setParameter("id", itemSupplierDto.getSupplierId());
//
//        PageRequest pageRequest = new PageRequest(itemSupplierDto.getPage(), itemSupplierDto.getCount());
//        query.setFirstResult(pageRequest.getOffset());
//        query.setMaxResults(pageRequest.getPageSize());
//
//        return query.getResultList();
//    }

}

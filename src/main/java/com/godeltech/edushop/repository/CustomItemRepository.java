package com.godeltech.edushop.repository;

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
}

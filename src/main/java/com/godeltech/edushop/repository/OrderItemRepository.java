package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by d.ihnatovich on 11/10/2017.
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

}

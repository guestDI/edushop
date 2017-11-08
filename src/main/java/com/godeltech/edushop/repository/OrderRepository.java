package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}

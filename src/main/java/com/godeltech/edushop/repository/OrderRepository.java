package com.godeltech.edushop.repository;

import com.godeltech.edushop.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.user.id = :id")
    List<Order> findByBuyerId(@Param("id") Long id);

    @Query("SELECT COUNT(*) FROM Order o WHERE o.user.id = :id")
    Long getOrdersCountByBuyerId(@Param("id") Long id);
}

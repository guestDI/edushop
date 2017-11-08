package com.godeltech.edushop.service;

import com.godeltech.edushop.converter.OrderConverter;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    public Order addOrder(OrderDTO orderDTO){
        return orderRepository.save(orderConverter.convert(orderDTO));
    }
}

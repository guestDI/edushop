package com.godeltech.edushop.service;

import com.godeltech.edushop.converter.OrderConverter;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.dto.SaveOrderDTO;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Order addOrder(SaveOrderDTO saveOrderDTO) {
        return orderRepository.save(orderConverter.convert(saveOrderDTO));
    }

    public List<OrderDTO> getAllOrderById(Long id) {
        return orderRepository.findByBuyerId(id).stream().map(it -> orderConverter.convert(it)).collect(Collectors.toList());
    }
}

package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.model.OrderItem;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.OrderItemRepository;
import com.godeltech.edushop.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Component
public class OrderConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Order convert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(Optional.of(orderDTO.getBuyerId()).map(userRepository::findOne).get());
        order.setOrderDate(new Date());

        List<OrderItem> orderItems = orderDTO.getItemIds().stream()
                .map(it -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(it.getQuantity());
                    orderItem.setItem(itemRepository.findOne(it.getId()));
                    orderItem.setOrder(order);
                    return orderItem;
                }).collect(Collectors.toList());
        order.setOrderItems(orderItems);

        return order;
    }
}

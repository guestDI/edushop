package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.ItemOrderDto;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.dto.SaveOrderDTO;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.model.OrderItem;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    @Autowired
    private ItemConverter itemConverter;

    public Order convert(SaveOrderDTO saveOrderDTO) {
        Order order = new Order();
        order.setUser(Optional.of(saveOrderDTO.getBuyerId()).map(userRepository::findOne).get());
        order.setOrderDate(new Date());

        List<OrderItem> orderItems = saveOrderDTO.getItemIds().stream()
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

    public OrderDTO convert(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setBuyerId(order.getUser().getId());
        orderDTO.setDate(order.getOrderDate());
        orderDTO.setItems(order.getOrderItems().stream()
                .map(itemConverter::convertItemForOrder).collect(Collectors.toList()));
        orderDTO.setTotalPrice(
                orderDTO.getItems().stream()
                        .map(ItemOrderDto::getPackPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        return orderDTO;
    }
}

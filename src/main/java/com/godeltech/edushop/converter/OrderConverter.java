package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return new Order(
                Optional.of(orderDTO.getBuyerId()).map(userRepository::findOne).get(),
                Lists.newArrayList(itemRepository.findAll(orderDTO.getItemIds())));
    }
}

package com.godeltech.edushop.service;

import com.godeltech.edushop.authentification.RoleEnum;
import com.godeltech.edushop.converter.OrderConverter;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.dto.SaveOrderDTO;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.OrderRepository;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.godeltech.edushop.authentification.RoleEnum.PREMIUM_BUYER;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderConverter orderConverter;

    public Order addOrder(SaveOrderDTO saveOrderDTO) {
        Order order = orderRepository.save(orderConverter.convert(saveOrderDTO));
        checkAndUpdateUserRole(saveOrderDTO.getBuyerId());
        return order;
    }

    public void checkAndUpdateUserRole(Long buyerId){
        User user = userRepository.findOne(buyerId);
        if(!PREMIUM_BUYER.getRoleName().equals(user.getRole().getName())) {
            int ordersCountByBuyerId = orderRepository.getOrdersCountByBuyerId(buyerId);

            if(ordersCountByBuyerId >= 3){
                userRepository.updateUserRole(buyerId, roleRepository.findByName(PREMIUM_BUYER.getRoleName()).getId());
            }
        }
    }

    public List<OrderDTO> getAllOrderById(Long id) {
        return orderRepository.findByBuyerId(id).stream().map(it -> orderConverter.convert(it)).collect(Collectors.toList());
    }
}

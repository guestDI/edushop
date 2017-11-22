package com.godeltech.edushop.service;

import com.godeltech.edushop.authentification.RoleEnum;
import com.godeltech.edushop.converter.OrderConverter;
import com.godeltech.edushop.dto.ItemQuantityDto;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.dto.SaveOrderDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.OrderRepository;
import com.godeltech.edushop.repository.RoleRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    private ItemRepository itemRepository;

    public Order addOrder(SaveOrderDTO saveOrderDTO) {
        if(checkItemsQuantity(saveOrderDTO.getItemIds())){
            Order order = orderRepository.save(orderConverter.convert(saveOrderDTO));
            checkAndUpdateUserRole(saveOrderDTO.getBuyerId());
            decreaseItemQuantity(saveOrderDTO.getItemIds());
            return order;
        } else {
            throw new RuntimeException("Cannot perform request");
        }

    }

    private void decreaseItemQuantity(List<ItemQuantityDto> itemIds) {
        Iterable<Item> itemRepositoryAll = itemRepository.findAll(itemIds.stream()
                .map(ItemQuantityDto::getId)
                .collect(Collectors.toList()));

        for (Item item : itemRepositoryAll) {
            Optional<ItemQuantityDto> dto = itemIds.stream()
                    .filter(it -> it.getId().equals(item.getId()))
                    .peek(it -> item.setQuantity(item.getQuantity() - it.getQuantity()))
                    .findFirst();
            if (!dto.isPresent()) {
                throw new RuntimeException("Something wrong");
            }
        }

        itemRepository.save(itemRepositoryAll);
    }

    private boolean checkItemsQuantity(List<ItemQuantityDto> itemIds) {
        Iterable<Item> itemRepositoryAll = itemRepository.findAll(itemIds.stream()
                .map(ItemQuantityDto::getId)
                .collect(Collectors.toList()));

        for (Item item : itemRepositoryAll) {
            Optional<ItemQuantityDto> dto = itemIds.stream()
                    .filter(it -> it.getId().equals(item.getId()))
                    .findFirst();
            if (!dto.filter(it -> it.getQuantity() <= item.getQuantity()).isPresent()) {
                return false;
            }
        }

        return true;
    }

    public void checkAndUpdateUserRole(Long buyerId){
        User user = userRepository.findOne(buyerId);
        if(!PREMIUM_BUYER.getRoleName().equals(user.getRole().getName())) {
            long ordersCountByBuyerId = orderRepository.getOrdersCountByBuyerId(buyerId);

            if(ordersCountByBuyerId >= 3L){
                userRepository.updateUserRole(buyerId, roleRepository.findByName(PREMIUM_BUYER.getRoleName()).getId());
            }
        }
    }

    public List<OrderDTO> getAllOrderById(Long id) {
        return orderRepository.findByBuyerId(id).stream().map(it -> orderConverter.convert(it)).collect(Collectors.toList());
    }

    public Long getOrdersCountById(Long id) {
        return orderRepository.getOrdersCountByBuyerId(id);
    }
}

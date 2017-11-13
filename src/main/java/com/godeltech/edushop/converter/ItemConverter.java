package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.ItemOrderDto;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.OrderItem;
import com.godeltech.edushop.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Component
public class ItemConverter {
    public List<ItemDTO> convertItem(List<Item> items) {
        return items.stream()
                .map((i) -> new ItemDTO(i.getId(), i.getCategory(), i.getManufacturer(), i.getName(), i.getDescription(),
                        i.getQuantity(), i.getPrice(), i.getDiscount()))
                .collect(Collectors.toList());
    }

    public ItemDTO convertItem(Item item) {
        return new ItemDTO(item.getId(), item.getCategory(), item.getManufacturer(), item.getName(), item.getDescription(),
                        item.getQuantity(), item.getPrice(), item.getDiscount());
    }

    public ItemOrderDto convertItemForOrder(OrderItem orderItem) {
        Item item = orderItem.getItem();
        return new ItemOrderDto(item.getId(), item.getCategory(), item.getManufacturer(), item.getName(), item.getDescription(),
                orderItem.getQuantity());
    }
}

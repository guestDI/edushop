package com.godeltech.edushop.service;

import com.godeltech.edushop.converter.ItemConverter;
import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Role;
import com.godeltech.edushop.repository.CategoryRepository;
import com.godeltech.edushop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by d.ihnatovich on 10/16/2017.
 */
@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemConverter itemConverter;

    public List<ItemDTO> getItemsByFilter(ItemFilter itemFilter){
        return itemConverter.convertItem(itemRepository.findItemsByFilter(itemFilter));
    }
}

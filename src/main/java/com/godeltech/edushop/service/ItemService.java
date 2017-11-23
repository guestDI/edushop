package com.godeltech.edushop.service;

import com.godeltech.edushop.converter.ItemConverter;
import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.dto.ItemFilterWithPaging;
import com.godeltech.edushop.dto.ItemSupplierDto;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.repository.CategoryRepository;
import com.godeltech.edushop.repository.CustomItemRepository;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomItemRepository customItemRepository;

    @Autowired
    private ItemConverter itemConverter;

    public List<ItemDTO> getItemsByFilter(ItemFilter itemFilter){
        return itemConverter.convertItem(itemRepository.findItemsByFilter(itemFilter));
    }

    public Long getItemsCountByFilter(ItemFilter itemFilter){
        return itemRepository.findItemsCountByFilter(itemFilter);
    }

    public List<ItemDTO> getItemsByFilterForPage(ItemFilterWithPaging itemFilterWithPaging){
        PageRequest pageRequest = new PageRequest(itemFilterWithPaging.getPage(), itemFilterWithPaging.getSize());
        if(itemFilterWithPaging.getItemFilter().getChildCategoryId()!=null){
            itemFilterWithPaging.getItemFilter().setCategoryId(itemFilterWithPaging.getItemFilter().getChildCategoryId());
        }

        return itemConverter.convertItem(itemRepository.findItemsByFilterAndPage(itemFilterWithPaging.getItemFilter(), pageRequest));
    }

    public List<ItemDTO> getItemsForPage(ItemSupplierDto itemSupplierDto){
        return itemConverter.convertItem(customItemRepository.findItemsForPage(itemSupplierDto));
    }

    public List<ItemDTO> getZeroItemsForPage(ItemSupplierDto itemSupplierDto){
        return itemConverter.convertItem(customItemRepository.findZeroItemsForPage(itemSupplierDto));
    }

    public Long getItemsCount(Long supplierId){
        return itemRepository.getItemsCount(supplierId);
    }

    public Long getCountOfItems(){
        return itemRepository.getCountOfAllItems();
    }

    public Long getCountOfItemsWithDiscount(){
        return itemRepository.getCountOfAllItemsWithDiscount();
    }

    public Long getCountOfItemsWithZeroQuantity(Long supplierId){
        return itemRepository.getCountOfAllItemsWithZeroQuantity(supplierId);
    }

    public ItemDTO addItem(Item item){
        item.setSupplier(userRepository.findOne(item.getSupplier().getId()));
        item.setCategory(categoryRepository.findOne(item.getCategory().getId()));
        return itemConverter.convertItem(itemRepository.save(item));
    }

    public void deleteItem(Long id){
        itemRepository.delete(id);
    }

    public void updateItem(ItemDTO itemDTO){
        itemRepository.update(itemDTO);
    }
}


package com.godeltech.edushop.controller;

import com.godeltech.edushop.converter.ItemConverter;
import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.ItemSupplierDto;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.service.ItemService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dmitry on 08.10.2017.
 */
@Transactional
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/items")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ItemService itemService;

//    @RequestMapping(value = "/getAllBySupplier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Item>> findAllBySupplier() {
//        return new ResponseEntity<>(itemRepository.findItemsForUser(), HttpStatus.FOUND);
//    }

    @RequestMapping(value = "/getAllBySupplier/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> findAllBySupplier(@PathVariable("id") Long id) {

        return new ResponseEntity<>(itemConverter.convertItem(Lists.newArrayList(itemRepository.findAll(Collections.singleton(id)))), HttpStatus.OK);

    }

    @RequestMapping(value = "/filterItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> filterItems(@RequestBody ItemFilter itemFilter) {
        return new ResponseEntity<>(itemService.getItemsByFilter(itemFilter), HttpStatus.OK);
    }

    @RequestMapping(value = "/getItemsCountByFilter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getItemsCountSearch(@RequestBody ItemFilter itemFilter) {
        return itemService.getItemsCountByFilter(itemFilter);
    }

    @RequestMapping(value = "/filterItemsBySupplier/{id}/{page}/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> filterItemsForPage(@PathVariable("id") Long id, @PathVariable("page") Integer page, @PathVariable("count") Integer count) {
        return new ResponseEntity<>(itemService.getItemsForPage(new ItemSupplierDto(id, page, count)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getItemsCount/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getItemsCount(@PathVariable("id") Long id) {
        return itemService.getItemsCount(id);

    }

}

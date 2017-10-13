package com.godeltech.edushop.controller;

import com.godeltech.edushop.converter.ItemConverter;
import com.godeltech.edushop.dto.ItemFilter;
import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.repository.ItemRepository;
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

//    @RequestMapping(value = "/getAllBySupplier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Item>> findAllBySupplier() {
//        return new ResponseEntity<>(itemRepository.findItemsForUser(), HttpStatus.FOUND);
//    }

    @RequestMapping(value = "/getAllBySupplier/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> findAllBySupplier(@PathVariable("id") Long id) {

        return new ResponseEntity<>(itemConverter.convertItem(Lists.newArrayList(itemRepository.findAll(Collections.singleton(id)))), HttpStatus.FOUND);

    }

    @RequestMapping(value = "/filterItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDTO>> filterItems(@RequestBody ItemFilter itemFilter) {

        return new ResponseEntity<>(itemConverter.convertItem(Lists.newArrayList(itemRepository.findItemsByFilter(itemFilter))), HttpStatus.FOUND);

    }
}
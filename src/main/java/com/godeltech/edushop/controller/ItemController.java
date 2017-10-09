package com.godeltech.edushop.controller;

import com.godeltech.edushop.converter.ItemConverter;
import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.UserDTO;
import com.godeltech.edushop.dto.UserProfileDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.RoleRepository;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
}

package com.godeltech.edushop.controller;

import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.User;
import com.godeltech.edushop.repository.ItemRepository;
import com.godeltech.edushop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getAllBySupplier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Item> findAllBySupplier() {

        return itemRepository.findItemsForUser();
    }

}

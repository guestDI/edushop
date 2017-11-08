package com.godeltech.edushop.controller;

import com.godeltech.edushop.dto.ItemDTO;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Transactional
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
    }
}

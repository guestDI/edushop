package com.godeltech.edushop.controller;

import com.godeltech.edushop.annotation.Permissions;
import com.godeltech.edushop.dto.OrderDTO;
import com.godeltech.edushop.dto.SaveOrderDTO;
import com.godeltech.edushop.model.Order;
import com.godeltech.edushop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addOrder(@RequestBody SaveOrderDTO saveOrderDTO) {
        orderService.addOrder(saveOrderDTO);
    }

//    @Permissions(roles = {"Administrator"})
    @RequestMapping(value = "/getOrders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrders(@PathVariable Long id) {
        return orderService.getAllOrderById(id);
    }

    @RequestMapping(value = "/getOrdersCount/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getOrdersCount(@PathVariable Long id) {
        return orderService.getOrdersCountById(id);
    }
}

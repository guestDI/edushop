package com.godeltech.edushop.controller;

import com.godeltech.edushop.model.Rate;
import com.godeltech.edushop.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Transactional
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/rates")
public class RateController {
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/getCurrentRates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rate getCurrentRate() {
        return rateService.getRate();
    }
}

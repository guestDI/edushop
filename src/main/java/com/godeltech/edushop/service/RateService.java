package com.godeltech.edushop.service;

import com.godeltech.edushop.converter.RateConverter;
import com.godeltech.edushop.dto.RateDTO;
import com.godeltech.edushop.model.Rate;
import com.godeltech.edushop.repository.RateRepository;
import com.godeltech.edushop.rest.NetClientGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Service
@Transactional
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RateConverter rateConverter;

    @Autowired
    private NetClientGet netClientGet;

    public Rate getRate(){
        return rateRepository.findFirst1ByOrderByDateRateDesc();
    }

    public Rate saveCurrentRate(){
        return rateRepository.save(rateConverter.convertRateItem(netClientGet.getRate()));
    }

}

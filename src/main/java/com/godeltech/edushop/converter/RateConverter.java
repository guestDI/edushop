package com.godeltech.edushop.converter;

import com.godeltech.edushop.dto.RateDTO;
import com.godeltech.edushop.model.Item;
import com.godeltech.edushop.model.Rate;
import org.springframework.stereotype.Component;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Component
public class RateConverter {
    public Rate convertRateItem(RateDTO rateDTO) {
        return new Rate(rateDTO.getDate(), rateDTO.getRates().getEUR(), rateDTO.getRates().getGBP());
    }

}

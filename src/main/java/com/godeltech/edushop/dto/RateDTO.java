package com.godeltech.edushop.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateDTO {
    private String base;
    private Date date;
    private Rates rates;

}

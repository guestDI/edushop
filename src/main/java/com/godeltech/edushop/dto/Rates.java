package com.godeltech.edushop.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rates {
    private BigDecimal GBP;
    private BigDecimal EUR;
}

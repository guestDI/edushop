package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Category;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by d.ihnatovich on 10/12/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFilter {
    private String manufacturer;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean discount;
    private Long categoryId;
}

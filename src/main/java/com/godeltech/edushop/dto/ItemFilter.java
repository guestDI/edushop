package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Category;
import lombok.*;
import org.springframework.util.StringUtils;

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
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean discount;
    private Boolean inStore;
    private Long categoryId;
    private Long childCategoryId;

    public String getDescription() {
        String temp = StringUtils.isEmpty(this.description) ? null : "%" + description + "%";
        return temp;
    }
}

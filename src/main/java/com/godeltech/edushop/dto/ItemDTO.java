package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Category;
import com.godeltech.edushop.model.Role;
import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by d.ihnatovich on 10/9/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private Long id;
    private Category category;
    private String manufacturer;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private int discount;
}
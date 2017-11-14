package com.godeltech.edushop.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.godeltech.edushop.model.Category;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by d.ihnatovich on 11/13/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrderDto {
    private Long id;
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="name")
    @JsonIdentityReference(alwaysAsId=true)
    private Category category;
    private String manufacturer;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal packPrice;
}

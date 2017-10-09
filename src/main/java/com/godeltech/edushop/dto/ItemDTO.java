package com.godeltech.edushop.dto;

import com.godeltech.edushop.model.Role;
import lombok.*;

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
    private String category;
    private String manufacturer;
    private String name;
    private String description;
    private double quantity;
    private double price;
    private double discount;
}

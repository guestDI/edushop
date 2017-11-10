package com.godeltech.edushop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by d.ihnatovich on 11/10/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemQuantityDto {
    private Long id;
    private int quantity;
}

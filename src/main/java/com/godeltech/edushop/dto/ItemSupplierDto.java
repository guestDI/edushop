package com.godeltech.edushop.dto;

import lombok.*;

/**
 * Created by d.ihnatovich on 10/19/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSupplierDto {
    private Long supplierId;
    private Integer page;
    private Integer count;
}

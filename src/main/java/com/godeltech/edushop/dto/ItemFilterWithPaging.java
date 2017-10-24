package com.godeltech.edushop.dto;

import lombok.*;

/**
 * Created by d.ihnatovich on 10/24/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFilterWithPaging {
    private ItemFilter itemFilter;
    private Integer size;
    private Integer page;
}

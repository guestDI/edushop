package com.godeltech.edushop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by d.ihnatovich on 11/8/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long buyerId;
    private List<Long> itemIds;
}

package com.godeltech.edushop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by d.ihnatovich on 11/13/2017.
 */
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long buyerId;
    private List<ItemOrderDto> items;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY'T'HH:mm")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date date;
}

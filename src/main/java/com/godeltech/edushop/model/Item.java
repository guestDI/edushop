package com.godeltech.edushop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private User supplier;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private int discount;

}

package com.godeltech.edushop.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by d.ihnatovich on 9/29/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private User supplier;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    private double discount;

}

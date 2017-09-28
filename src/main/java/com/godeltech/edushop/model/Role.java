package com.godeltech.edushop.model;

import javax.persistence.*;

/**
 * Created by d.ihnatovich on 9/28/2017.
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}

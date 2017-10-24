package com.godeltech.edushop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by d.ihnatovich on 10/12/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(Category parentCategory, String name, String description) {
        this.parentCategory = parentCategory;
        this.name = name;
        this.description = description;
    }
}

package com.godeltech.edushop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmitry on 07.11.2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rate")
@Builder
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateRate = new Date();

    @Column(nullable = false)
    private BigDecimal dollar;

    @Column(precision=12, scale=4)
    private BigDecimal euro;

    @Column(precision=12, scale=4)
    private BigDecimal pound;

    @PrePersist
    public void prePersist() {
        this.dollar = new BigDecimal(1);
//        this.dateRate = new Date();
    }

    public Rate(BigDecimal euro, BigDecimal pound) {
        this.euro = euro;
        this.pound = pound;
    }

    public Rate(Date dateRate, BigDecimal euro, BigDecimal pound) {
        this.dateRate = dateRate;
        this.euro = euro;
        this.pound = pound;
    }
}

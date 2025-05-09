package com.nexeyo.erp.DeliveryTerms;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DeliveryTerms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String name;
    @Column
    private String content;

}

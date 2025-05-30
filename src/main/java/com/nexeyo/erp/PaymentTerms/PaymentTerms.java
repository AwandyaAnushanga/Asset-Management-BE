package com.nexeyo.erp.PaymentTerms;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PaymentTerms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String name;
    @Column
    private String terms;

}

package com.nexeyo.erp.PaymentTypes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PaymentTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    private String type_name;

}

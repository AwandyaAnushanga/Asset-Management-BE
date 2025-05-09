package com.nexeyo.erp.CategoryFields;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CategoryFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private int category_id;
    @Column
    private int field_id;

    //for short key
    @Column
    private String fullShortKey;
}

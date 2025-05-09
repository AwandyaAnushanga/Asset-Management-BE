package com.nexeyo.erp.Incoterms;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Incoterms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String name;
    @Column
    private String content;

}

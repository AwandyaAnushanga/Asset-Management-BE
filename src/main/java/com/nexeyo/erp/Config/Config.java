package com.nexeyo.erp.Config;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "`key`")
    private String key;
    @Column
    private String value;
}

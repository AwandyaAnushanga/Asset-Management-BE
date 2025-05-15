package com.nexeyo.erp.SystemSettings;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SystemSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private String field;
    @Column
    private String field_value;

    @Transient
    Object content;

}

package com.nexeyo.erp.MaintenanceType;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MaintenanceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
}

package com.nexeyo.erp.DisposeTypes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DisposeTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    private String name;
    private String description;
}

package com.nexeyo.erp.SystemTypes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class SystemTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String typeName;
    @Column
    private String startCharacter;

}

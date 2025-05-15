package com.nexeyo.erp.Technician;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String technicianNo;
    @Column
    private Integer technicianNoWithoutCharacter;
    @Column
    private String companyName;
    @Column
    private Double totalServiceCost;
}

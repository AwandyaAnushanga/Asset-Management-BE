package com.nexeyo.erp.SupplierAddress;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SupplierAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private Integer supplier_id;
    @Column
    private String country;
    @Column
    private String name;
    @Column
    private String street_address;
    @Column
    private String street_address2;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String zip_code;
    @Column
    private String mobile_number;
    @Column
    private Integer address_type;


}

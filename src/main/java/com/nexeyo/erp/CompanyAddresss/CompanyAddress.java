package com.nexeyo.erp.CompanyAddresss;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompanyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    private int company_id;
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
    private String company_email;


}

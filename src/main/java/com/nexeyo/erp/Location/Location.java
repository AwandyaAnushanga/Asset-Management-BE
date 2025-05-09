package com.nexeyo.erp.Location;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    Integer type;
    @Column
    private String name;
    @Column
    private String country;
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
    private String email;
    @Column
    private Integer payable_account;
    @Column
    private Integer receivable_account;
    @Column
    private Integer cash_account;
    @Column
    private Integer retail_sale_account;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean enabled;
    @Column
    private String branch_code;
    @Column
    private Integer branch_code_without_characters;

    @Column
    private Integer pos_bill_start_from;
    @Column
    private String pos_bill_start_character;

}

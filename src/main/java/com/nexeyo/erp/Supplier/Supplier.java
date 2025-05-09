package com.nexeyo.erp.Supplier;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String supplierNo;
    @Column
    private Integer supplierNoWithoutCharacters;
    @Column
    private String email_address;
    @Column
    private String company;
    @Column
    private Integer payment;
    @Column
    private Integer payment_terms;
    @Column
    private Integer delivery_terms;
    @Column
    private Integer delivery_conditions;
    @Column
    private String tel;
    @Column
    private Integer incoterms;
    @Column
    private LocalDateTime create_at;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean enable;
    @Column
    private Integer partner_type;
    @Column
    private String vat_registration_number;
    @Column
    private String supplier_country;
    @Column
    private String contact_person;
    @Column
    private String currency;
}

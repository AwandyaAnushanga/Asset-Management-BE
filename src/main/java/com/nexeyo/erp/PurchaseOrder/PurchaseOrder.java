package com.nexeyo.erp.PurchaseOrder;

import com.nexeyo.erp.CompanyAddresss.CompanyAddress;
//import com.nexeyo.erp.CurrentCurrency.CurrentCurrency;
import com.nexeyo.erp.DeliveryTerms.DeliveryTerms;
import com.nexeyo.erp.Incoterms.Incoterms;
import com.nexeyo.erp.Location.Location;
import com.nexeyo.erp.PaymentTerms.PaymentTerms;
import com.nexeyo.erp.PaymentTypes.PaymentTypes;
import com.nexeyo.erp.PurchaseOrderItems.PurchaseOrderItem;
import com.nexeyo.erp.Supplier.Supplier;
import com.nexeyo.erp.SupplierAddress.SupplierAddress;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "po_no"),})
public class PurchaseOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private LocalDate po_date; //document date
    @Column
    private String po_no;
    @Column
    private Integer supplier_id;
    @Column
    private Integer supplier_address_id;
    @Column
    private String tax_registration_no;
    @Column
    private Integer location_id;
    @Column
    private String contact_person;
    @Column
    private String delivery_tel_no;
    @Column
    private LocalDate expected_delivery_date; //delivery date
    @Column
    private String delivery_address;
    @Column
    private double sub_total;
    @Column
    private double total;
    @Column
    private double total_discount;
    @Column
    private double tax;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean cancelled;
    @Column
    private String quotation_no;
    @Column
    private Integer status;
    @Column
    private Integer back_order_status;
    @Column
    private Integer delivery_address_from_id;
    @Column
    private int po_no_without_characters;
    @Column
    private LocalDateTime create_at;
    @Column
    private Integer payment_terms;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean approved;
    @Column
    private Integer approved_user;
    @Column
    private String remark;
    @Column
    private Integer delivery_terms;
    @Column
    private Integer payment;
    @Column
    private String tel;
    @Column
    private Integer incoterms;
    @Column
    private Integer currency_rate_id;
    @Column
    private Integer system_currency_rate_id;
    @Transient
    private Integer currency_rate_header_id;

    @ApiModelProperty(hidden = true)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;

    @ApiModelProperty(hidden = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_from_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CompanyAddress companyAddress;


//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "currency_rate_id", referencedColumnName = "id" , insertable = false,updatable = false)
//    private CurrentCurrency currentCurrency;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "system_currency_rate_id", referencedColumnName = "id",insertable = false,updatable = false)
//    private CurrentCurrency systemCurrentCurrency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<PurchaseOrderItem> purchaseOrderItemsList;

    @ApiModelProperty(hidden = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Supplier suppliers;

    @ApiModelProperty(hidden = true)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_address_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SupplierAddress supplierSelectedAddress;

    @Transient
    PaymentTerms payment_terms_detail;
    @Transient
    DeliveryTerms delivery_terms_details;
    @Transient
    PaymentTypes paymentTypes;
    @Transient
    Incoterms incotermsDetails;


}

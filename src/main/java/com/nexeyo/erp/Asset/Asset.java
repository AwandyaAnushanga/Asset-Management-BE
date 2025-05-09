package com.nexeyo.erp.Asset;

import com.nexeyo.erp.AssetStatus.AssetStatus;
import com.nexeyo.erp.Custodian.Custodian;
import com.nexeyo.erp.Location.Location;
import com.nexeyo.erp.Supplier.Supplier;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer custodian_id;
    @Column
    private Integer location_id;
    @Column
    private String name;
    @Column
    private String assertNo;
    @Column
    private Integer assertNoWithoutCharacters;
    @Column(unique = true)
    private String assetTag;
    @Column
    private String description;
    @Column
    private LocalDateTime createAt;
    @Column
    private Integer supplier_id;
    @Column
    private Integer qty;
    @Column
    private Double value;
    @Column
    private Double totalValue;
    @Column
    private Integer status;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean disposed;
    @Column
    private LocalDateTime disposalDate;
    @Column
    private String disposalDescription;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id" , referencedColumnName = "id", insertable = false,updatable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "custodian_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Custodian custodian;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "id", insertable = false,updatable = false)
    private AssetStatus assetStatus;
}

package com.nexeyo.erp.PurchaseOrderItems;

//import com.nexeyo.erp.Item.Item;
import com.nexeyo.erp.UnitOfMeasurement.UnitOfMeasurement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private int order_id;
    @Column
    private Integer item_id;
    @Column
    private Double quantity;
    @Column
    private Double cost_price;
    @Column
    private Double discount;
    @Column
    private Double amount;
    @Column
    private Double tax_rate;
    @Column
    private Double tax_amount;
    @Column
    private Double received_quantity;
    @Column
    private Double remaining_quantity;
    @Column
    private Integer status;
    @Column
    private Double without_vat;
    @Column
    private Double unit;
    @Column
    private String description;
    @Column
    private Integer uom;

//    @ApiModelProperty(hidden = true)
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Item item;
    @Transient
    UnitOfMeasurement unitOfMeasurement;

}

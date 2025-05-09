package com.nexeyo.erp.PurchaseOrder;

import lombok.Data;

@Data
public class PurchaseOrderReportModel {
    int id;
    String description;
    String qty;
    String unitprice;
    String total;
}

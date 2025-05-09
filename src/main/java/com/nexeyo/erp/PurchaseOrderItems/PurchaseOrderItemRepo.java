package com.nexeyo.erp.PurchaseOrderItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemRepo extends JpaRepository<PurchaseOrderItem,Integer> {
    @Query("select p from PurchaseOrderItem p where p.order_id = ?1")
    List<PurchaseOrderItem> findByOrder_id(int order_id);
}

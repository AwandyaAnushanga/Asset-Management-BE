package com.nexeyo.erp.SupplierAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAddressRepo extends JpaRepository<SupplierAddress,Integer> {
    @Query("select s from SupplierAddress s where s.supplier_id = ?1")
    List<SupplierAddress> findBySupplier_id(int supplier_id);
}

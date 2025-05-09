package com.nexeyo.erp.SupplierAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplierAddressService {
    @Autowired
    SupplierAddressRepo supplierAddressRepo;

    public ResponseEntity<?> Add(SupplierAddress supplierAddress) {
        supplierAddressRepo.save(supplierAddress);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<?> Update(SupplierAddress supplierAddress) {
        supplierAddressRepo.save(supplierAddress);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<?> FindBySupplierID(int supplierId) {
        return ResponseEntity.ok(supplierAddressRepo.findBySupplier_id(supplierId));
    }

}

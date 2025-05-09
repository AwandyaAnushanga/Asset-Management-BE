package com.nexeyo.erp.SupplierAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/suppliers-address/addresses/")
public class SupplierAddressController {
    @Autowired
    SupplierAddressService supplierAddressService;

    @PostMapping(path = "address")
    ResponseEntity<?> Add(@RequestBody SupplierAddress supplierAddress) {
        return supplierAddressService.Add(supplierAddress);
    }

    @PutMapping(path = "address")
    ResponseEntity<?> Update(@RequestBody SupplierAddress supplierAddress) {
        return supplierAddressService.Add(supplierAddress);
    }

    @GetMapping(path = "address-search/{supplier_id}")
    ResponseEntity<?> GetBySupplierId(@PathVariable int supplier_id) {
        return supplierAddressService.FindBySupplierID(supplier_id);
    }
}

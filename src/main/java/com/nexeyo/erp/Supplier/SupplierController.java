package com.nexeyo.erp.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping(" ")
    public ResponseEntity<?> addSupplier(@RequestBody Supplier supplier) {
        return supplierService.Add(supplier);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAllSuppliers(
            @RequestParam int page,
            @RequestParam int page_size,
            @RequestParam int order
    ) {
        return supplierService.GetALl(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable int id) {
        return supplierService.GetById(id);
    }
}

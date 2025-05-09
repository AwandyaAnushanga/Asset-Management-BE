package com.nexeyo.erp.Custodian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/custodian")
public class CustodianController {

    @Autowired
    private CustodianService custodianService;

    @PostMapping("")
    public ResponseEntity<?> addCustodian(@RequestBody Custodian custodian) {
        return custodianService.Add(custodian);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAllCustodians(@RequestParam int page, @RequestParam int page_size, @RequestParam int order
    ) {
        return custodianService.GetALl(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustodianById(@PathVariable int id) {
        return custodianService.GetById(id);
    }
}

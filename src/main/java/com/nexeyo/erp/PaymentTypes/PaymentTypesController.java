package com.nexeyo.erp.PaymentTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentTypesController {
    @Autowired
    PaymentTypesService paymentTypesService;

    @GetMapping(path = "/payment/{page}/{page_size}")
    ResponseEntity<?> GetAll(@PathVariable int page, @PathVariable int page_size) {
        return paymentTypesService.GetAll(page, page_size);
    }

    @PostMapping(path = "")
    ResponseEntity<?> Save(@RequestBody PaymentTypes paymentTypes) {
        return paymentTypesService.Save(paymentTypes);
    }


}

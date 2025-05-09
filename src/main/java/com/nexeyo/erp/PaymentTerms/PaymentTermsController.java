package com.nexeyo.erp.PaymentTerms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/payment-terms")
public class PaymentTermsController {
    @Autowired
    PaymentTermsService paymentTermsService;

    @GetMapping(path = "/{page}/{page_size}")
    public ResponseEntity<?> GetAll(@PathVariable int page, @PathVariable int page_size) {
        return paymentTermsService.GetAll(page, page_size);
    }

    @PostMapping(path = "")
    public ResponseEntity<?> Save(@RequestBody PaymentTerms paymentTerms) {
        return paymentTermsService.Save(paymentTerms);
    }
}

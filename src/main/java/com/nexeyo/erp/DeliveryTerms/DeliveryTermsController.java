package com.nexeyo.erp.DeliveryTerms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/delivery-terms")
public class DeliveryTermsController {
    @Autowired
    DeliveryTermsService deliveryTermsService;

    @GetMapping(path = "/{page}/{page_size}")
    ResponseEntity<?> GetAll(@PathVariable int page, @PathVariable int page_size) {
        return deliveryTermsService.GetAll(page, page_size);
    }

    @PostMapping(path = "")
    ResponseEntity<?> Save(@RequestBody DeliveryTerms deliveryTerms) {
        return deliveryTermsService.Save(deliveryTerms);
    }


}

package com.nexeyo.erp.PaymentTerms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentTermsService {
    @Autowired
    PaymentTermsRepo paymentTermsRepo;

    public ResponseEntity<?> GetAll(int page, int page_size) {
        return ResponseEntity.ok(paymentTermsRepo.findAll(PageRequest.of(page, page_size)));
    }

    public ResponseEntity<?> Save(PaymentTerms paymentTerms) {
        paymentTermsRepo.save(paymentTerms);
        return ResponseEntity.ok("success");
    }
}

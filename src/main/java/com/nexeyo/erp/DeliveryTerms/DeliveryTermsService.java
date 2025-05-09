package com.nexeyo.erp.DeliveryTerms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTermsService {
    Logger logger = LoggerFactory.getLogger(DeliveryTermsService.class);

    @Autowired
    DeliveryTermsRepo deliveryTermsRepo;

    public ResponseEntity<?> GetAll(int page, int pageSize) {
        try {
            Page<DeliveryTerms> deliveryTerms = deliveryTermsRepo.findAll(PageRequest.of(page, pageSize));
            logger.info("GetAll - Success");
            return ResponseEntity.ok(deliveryTerms);
        } catch (Exception e) {
            logger.error("GetAll - Error " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);
        }
    }

    public ResponseEntity<?> Save(DeliveryTerms deliveryTerms) {
        try {
            logger.info(deliveryTerms.toString());
            DeliveryTerms deliveryTerms1 = deliveryTermsRepo.save(deliveryTerms);
            logger.info("Save - Success");
            return ResponseEntity.ok(deliveryTerms1);
        } catch (Exception e) {
            logger.error("Save - Error : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Save - Error " + e);
        }

    }
}

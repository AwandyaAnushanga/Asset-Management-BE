package com.nexeyo.erp.PaymentTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypesService {
    Logger logger = LoggerFactory.getLogger(PaymentTypesService.class);

    @Autowired
    PaymentTypesRepo paymentTypesRepo;

    public ResponseEntity<?> GetAll(int page, int page_size) {
        try{
            Page<PaymentTypes> paymentTypesList =paymentTypesRepo.findAll(PageRequest.of(page, page_size));
            logger.info("GetAll - "+paymentTypesList);
            logger.info("GetAll - Success");
            return ResponseEntity.ok(paymentTypesList);
        }catch (Exception e){
            logger.error("GetAll Error : "+e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("GetAll Error - "+e);
        }

    }

    public ResponseEntity<?> Save(PaymentTypes paymentTypes) {
        try{
            logger.info("Save - "+paymentTypes);
            PaymentTypes paymentTypes1 =paymentTypesRepo.save(paymentTypes);
            logger.info("Save - Success");
            return ResponseEntity.ok(paymentTypes1);
        }catch (Exception e){
            logger.error("Save Error : "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("GetAll Error - "+e);
        }
    }

}

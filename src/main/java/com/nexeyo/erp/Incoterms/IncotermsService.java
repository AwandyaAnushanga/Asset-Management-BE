package com.nexeyo.erp.Incoterms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IncotermsService {
    Logger logger = LoggerFactory.getLogger(IncotermsService.class);

    @Autowired
    IncotermsRepo incotermsRepo;

    public ResponseEntity<?> GetAll(int page, int page_size) {
        try {
            Page<Incoterms> incotermsPage = incotermsRepo.findAll(PageRequest.of(page, page_size));
            logger.info("GetAll - Success");
            return ResponseEntity.ok(incotermsPage);

        } catch (Exception e) {
            logger.error("GetAll - Error : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);

        }
    }

    public ResponseEntity<?> Save(Incoterms incoterms) {
        try{

            Incoterms incoterms1= incotermsRepo.save(incoterms);
            logger.info("Save - Success");
            return ResponseEntity.ok(incoterms1);

        }catch (Exception e){
            logger.error("Save : Error "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error "+e);
        }
    }

    public ResponseEntity<?> FindAll(int page, int pageSize) {
        try {
            Page<Incoterms> incotermsPage = incotermsRepo.findAll(PageRequest.of(page, pageSize));
            logger.info("GetAll - Success");
            return ResponseEntity.ok(incotermsPage);

        } catch (Exception e) {
            logger.error("GetAll - Error : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e);

        }
    }
}

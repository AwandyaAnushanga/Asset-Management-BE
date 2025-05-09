package com.nexeyo.erp.CompanyAddresss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyAddressService {
    Logger logger = LoggerFactory.getLogger(CompanyAddressService.class);

    @Autowired
    CompanyAddressRepo companyAddressRepo;

    public ResponseEntity<?>GetAll(int page, int page_size){
        try{
            logger.info("GetAll called with page: " + page + ", page_size: " + page_size);
            Page<CompanyAddress> companyAddressList =companyAddressRepo.findAll(PageRequest.of(page,page_size));
            logger.info("GetAll successfully");
            return ResponseEntity.ok(companyAddressList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("GetAll Error : "+e);
        }
    }

    public ResponseEntity<?> SaveAll(List<CompanyAddress> companyAddressList) {
        try{
            List<CompanyAddress> companyAddresses = companyAddressRepo.saveAll(companyAddressList);
            logger.info("GetAll successfully");
            return ResponseEntity.ok(companyAddresses);
        }catch (Exception e){
            logger.info("SaveAll Error : "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SaveAll Error : "+e);
        }
    }
}

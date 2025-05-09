package com.nexeyo.erp.CompanyAddresss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company-address/")
public class CompanyAddressController {
    Logger logger = LoggerFactory.getLogger(CompanyAddressController.class);
    @Autowired
    CompanyAddressService companyAddressService;

    @GetMapping(path = "address/{page}/{page_size}")
    public ResponseEntity<?> GetAll(@PathVariable int page, @PathVariable int page_size) {
        return companyAddressService.GetAll(page, page_size);
    }

    @PostMapping(path = "address")
    public ResponseEntity<?> Save(@RequestBody List<CompanyAddress> companyAddress) {
        logger.info(companyAddress.toString());
        return companyAddressService.SaveAll(companyAddress);
    }

}

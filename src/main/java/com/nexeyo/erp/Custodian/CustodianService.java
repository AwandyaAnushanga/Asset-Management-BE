package com.nexeyo.erp.Custodian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustodianService {

    @Autowired
    private CustodianRepo custodianRepo;

    public ResponseEntity<?> Add (Custodian custodian){
        return ResponseEntity.ok(custodianRepo.save(custodian));
    }

    public ResponseEntity<?> GetALl (int page, int page_size, int order){
        return ResponseEntity.ok(custodianRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(custodianRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }
}

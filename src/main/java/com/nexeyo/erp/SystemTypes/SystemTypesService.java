package com.nexeyo.erp.SystemTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SystemTypesService {

    @Autowired
    private SystemTypesRepo systemTypesRepo;

    public ResponseEntity<?> addTypes (SystemTypes systemTypes){
        return ResponseEntity.ok(systemTypesRepo.save(systemTypes));
    }

    public ResponseEntity<?> getAllWithPagination (int page, int pageSize, int order){
        return ResponseEntity.ok(systemTypesRepo.findAll(PageRequest.of(page, pageSize, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));

    }

    public ResponseEntity<?> getById (int id){
        return ResponseEntity.ok(systemTypesRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid id")
        ));
    }

    public ResponseEntity<?> update(SystemTypes systemTypes){
        systemTypesRepo.findById(systemTypes.getId()).orElseThrow(
                ()-> new RuntimeException("Invalid id")
        );

        return ResponseEntity.ok(systemTypesRepo.save(systemTypes));
    }
}

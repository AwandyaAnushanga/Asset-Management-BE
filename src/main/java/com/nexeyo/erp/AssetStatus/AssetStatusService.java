package com.nexeyo.erp.AssetStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AssetStatusService {

    @Autowired
    private AssetStatusRepo assetStatusRepo;

    public ResponseEntity<?> add(AssetStatus assetStatus) {

        return ResponseEntity.ok(assetStatusRepo.save(assetStatus));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(assetStatusRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));

    }

    public ResponseEntity<?> getById(int id) {
        return ResponseEntity.ok(
                assetStatusRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID!"))
        );
    }
}

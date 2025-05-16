package com.nexeyo.erp.DisposeTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DisposeTypesService {

    @Autowired
    private DisposeTypesRepo disposeTypesRepo;

    public ResponseEntity<?> Add (DisposeTypes disposeTypes){
        return ResponseEntity.ok(disposeTypesRepo.save(disposeTypes));
    }

    public ResponseEntity<?> GetAll (int page, int page_size,int order){
        return ResponseEntity.ok(disposeTypesRepo.findAll(PageRequest.of(page,page_size, Sort.by(order == 0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(disposeTypesRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }

}

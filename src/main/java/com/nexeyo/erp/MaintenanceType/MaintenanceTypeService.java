package com.nexeyo.erp.MaintenanceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceTypeService {

    @Autowired
    private MaintenanceTypeRepo maintenanceTypeRepo;

    public ResponseEntity<?> Add (MaintenanceType maintenanceType){
        return ResponseEntity.ok(maintenanceTypeRepo.save(maintenanceType));
    }

    public ResponseEntity<?> GetAll (int page, int page_size, int order){
        return ResponseEntity.ok(maintenanceTypeRepo.findAll(PageRequest.of(page,page_size, Sort.by(order==0? Sort.Direction.ASC : Sort.Direction.DESC, "id"))));
    }

    public ResponseEntity<?> GetById (int id){
        return ResponseEntity.ok(maintenanceTypeRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("Invalid Id!!!")
        ));
    }
}

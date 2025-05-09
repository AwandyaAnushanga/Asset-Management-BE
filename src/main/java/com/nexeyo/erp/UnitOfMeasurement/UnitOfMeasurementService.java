package com.nexeyo.erp.UnitOfMeasurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UnitOfMeasurementService {
    @Autowired
    UnitOfMeasurementRepo unitOfMeasurementRepo;

    public ResponseEntity<?> Add(UnitOfMeasurement unitOfMeasurement) {
        unitOfMeasurement.setCreate_at(LocalDateTime.now());
        return ResponseEntity.ok(unitOfMeasurementRepo.save(unitOfMeasurement));
    }


    public ResponseEntity<?> GetAll() {
        return ResponseEntity.ok(unitOfMeasurementRepo.findAll());
    }
}

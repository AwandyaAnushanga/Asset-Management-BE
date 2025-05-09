package com.nexeyo.erp.UnitOfMeasurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/unit-of-measurement")
public class UnitOfMeasurementController {

    @Autowired
    UnitOfMeasurementService unitOfMeasurementService;

    @PostMapping(path = "")
    ResponseEntity<?>AddUnitOfMeasurement(@RequestBody UnitOfMeasurement unitOfMeasurement){
        return unitOfMeasurementService.Add(unitOfMeasurement);
    }

    @GetMapping(path = "")
    ResponseEntity<?> GetUnitOfMeasurement() {
        return unitOfMeasurementService.GetAll();
    }



}

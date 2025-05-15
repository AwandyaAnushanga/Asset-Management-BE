package com.nexeyo.erp.MaintenanceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/maintenance-type")
public class MaintenanceTypeController {

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody MaintenanceType maintenanceType){
        return maintenanceTypeService.Add(maintenanceType);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return ResponseEntity.ok(maintenanceTypeService.GetAll(page, page_size, order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetALl (@PathVariable int id){
        return ResponseEntity.ok(maintenanceTypeService.GetById(id));
    }
}

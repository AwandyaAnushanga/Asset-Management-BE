package com.nexeyo.erp.AssetMaintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asset-maintenance")
public class AssetMaintenanceController {

    @Autowired
    private AssetMaintenanceService assetMaintenanceService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody List<AssetMaintenance> assetMaintenanceList){
        return assetMaintenanceService.Add(assetMaintenanceList);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return assetMaintenanceService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id ){
        return assetMaintenanceService.GetById(id);
    }

    @PutMapping("/add-maintenance-cost")
    public ResponseEntity<?> AddMaintenanceCost (@RequestParam int id, @RequestParam double cost){
        return assetMaintenanceService.AddMaintenanceCost(id, cost);
    }
}

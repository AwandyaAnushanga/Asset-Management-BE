package com.nexeyo.erp.AssetStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset-status")
public class AssetStatusController {

    @Autowired
    private AssetStatusService assetStatusService;

    @PostMapping(" ")
    public ResponseEntity<?> addAssetStatus(@RequestBody AssetStatus assetStatus) {
        return assetStatusService.add(assetStatus);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAllAssetStatuses(@RequestParam int page, @RequestParam int pageSize, @RequestParam int order
    ) {
        return assetStatusService.GetAll(page, pageSize, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetStatusById(@PathVariable int id) {
        return assetStatusService.getById(id);
    }
}

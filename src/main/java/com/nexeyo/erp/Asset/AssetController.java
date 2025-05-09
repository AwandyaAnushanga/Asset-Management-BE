package com.nexeyo.erp.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("")
    public ResponseEntity<?> addAsset(@RequestBody Asset asset) {
        return assetService.Add(asset);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAllAssets(@RequestParam int page, @RequestParam int page_size, @RequestParam int order
    ) {
        return assetService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable int id) {
        return assetService.GetById(id);
    }
}

package com.nexeyo.erp.AssetHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset")
public class AssetHeaderController {

    @Autowired
    private AssetHeaderService assetHeaderService;

    @PostMapping("")
    public ResponseEntity<?> addAsset(@RequestBody AssetHeader assetHeader) {
        return assetHeaderService.Add(assetHeader);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAllAssets(@RequestParam int page, @RequestParam int page_size, @RequestParam int order
    ) {
        return assetHeaderService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable int id) {
        return assetHeaderService.GetById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> Update (@RequestBody AssetHeader assetHeader){
        return assetHeaderService.Update(assetHeader);
    }

    @PutMapping("/update-value")
    public ResponseEntity<?> UpdateValue (@RequestBody AssetHeader assetHeader){
        return assetHeaderService.updateValue(assetHeader);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> Approve (@PathVariable int id){
        return assetHeaderService.Approve(id);
    }

}

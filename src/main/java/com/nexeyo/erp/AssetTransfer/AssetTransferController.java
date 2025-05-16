package com.nexeyo.erp.AssetTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset-transfer")
public class AssetTransferController {

    @Autowired
    private AssetTransferService assetTransferService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody AssetTransfer assetTransfer){
        return assetTransferService.Add(assetTransfer);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return assetTransferService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id){
        return assetTransferService.GetById(id);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> Approve (@PathVariable int id){
        return assetTransferService.Approve(id);
    }
}

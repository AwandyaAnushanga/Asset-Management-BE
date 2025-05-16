package com.nexeyo.erp.AssetImpairment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset-impairment")
public class AssetImpairmentController {

    @Autowired
    private AssetImpairmentService assetImpairmentService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody AssetImpairment assetImpairment){
        return assetImpairmentService.Add(assetImpairment);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> Approve (@PathVariable int id){
        return assetImpairmentService.Approve(id);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return assetImpairmentService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id){
        return assetImpairmentService.GetById(id);
    }
}

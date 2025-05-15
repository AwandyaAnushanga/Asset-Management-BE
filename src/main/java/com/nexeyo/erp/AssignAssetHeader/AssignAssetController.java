package com.nexeyo.erp.AssignAssetHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assign-asset")
public class AssignAssetController {

    @Autowired
    private AssignAssetHeaderService assignAssetHeaderService;

    @PostMapping
    public ResponseEntity<?> Add (@RequestBody AssignAssetHeader assignAssetHeader){
        return assignAssetHeaderService.Add(assignAssetHeader);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return assignAssetHeaderService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id){
        return assignAssetHeaderService.GetById(id);
    }
}

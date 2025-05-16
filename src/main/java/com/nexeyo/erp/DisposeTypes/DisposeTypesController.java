package com.nexeyo.erp.DisposeTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dispose-types")
public class DisposeTypesController {

    @Autowired
    private DisposeTypesService disposeTypesService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody DisposeTypes disposeTypes){
        return disposeTypesService.Add(disposeTypes);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return disposeTypesService.GetAll(page, page_size, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id){
        return disposeTypesService.GetById(id);
    }


}

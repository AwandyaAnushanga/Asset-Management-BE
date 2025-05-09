package com.nexeyo.erp.SystemTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/system-types")
public class SystemTypesController {

    @Autowired
    SystemTypesService systemTypesService;

    @PostMapping("")
    public ResponseEntity<?> addType (@RequestBody SystemTypes systemTypes){
        return systemTypesService.addTypes(systemTypes);
    }

    @GetMapping("/getAll/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAll (@PathVariable int page,
                                     @PathVariable(name = "page_size") int pageSize,
                                     @PathVariable int order){
        return systemTypesService.getAllWithPagination(page, pageSize, order);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById (@PathVariable int id){
        return systemTypesService.getById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody SystemTypes systemTypes){
        return systemTypesService.update(systemTypes);
    }
}

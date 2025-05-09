package com.nexeyo.erp.NumberingSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/numbering-system")
public class NumberingSystemController {

    @Autowired
    private NumberingSystemService numberingSystemService;

    @PostMapping("")
    public ResponseEntity<?> addNumbering (@RequestBody NumberingSystem numberingSystem){
        return numberingSystemService.addNumbering(numberingSystem);
    }

    @GetMapping("get/{page}/{page_size}/{order}")
    public ResponseEntity<?> getAll (@PathVariable int page,
                                     @PathVariable(name = "page_size") int pageSize,
                                     @PathVariable int order){

        return numberingSystemService.getAllWithPagination(page,pageSize,order);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById (@PathVariable int id){
        return numberingSystemService.getById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> update (@RequestBody NumberingSystem numberingSystem){
        return numberingSystemService.update(numberingSystem);
    }

}

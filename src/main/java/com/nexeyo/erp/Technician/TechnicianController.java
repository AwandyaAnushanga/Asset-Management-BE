package com.nexeyo.erp.Technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/technician")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody Technician technician){
        return technicianService.Add(technician);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById (@PathVariable int id){
        return technicianService.GetById(id);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> GetAll (@PathVariable int page, @PathVariable int page_size, @PathVariable int order){
        return technicianService.GetAll(page, page_size, order);
    }
}

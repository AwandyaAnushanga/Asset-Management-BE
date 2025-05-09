package com.nexeyo.erp.Incoterms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/incoterms")
public class IncotermsController {
    @Autowired
    IncotermsService incotermsService;

    @GetMapping(path = "/{page}/{page_size}")
    ResponseEntity<?> GetAll(@PathVariable int page, @PathVariable int page_size) {
        return incotermsService.FindAll(page, page_size);
    }

    @PostMapping(path = "")
    ResponseEntity<?> Save(@RequestBody Incoterms incoterms) {
        return incotermsService.Save(incoterms);
    }

}

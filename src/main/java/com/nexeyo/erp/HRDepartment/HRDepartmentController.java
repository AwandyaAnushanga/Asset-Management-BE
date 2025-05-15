package com.nexeyo.erp.HRDepartment;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/hr-department-fetch")

public class HRDepartmentController {
    @Autowired
    HRDepartmentService hrDepartmentService;


    @GetMapping(path = "")
    ResponseEntity<?> Fetch() throws JSONException {
        return hrDepartmentService.Fetch();
    }

    @PostMapping(path = "")
    ResponseEntity<?> Add(@RequestBody HRDepartment hrDepartment) {
        return hrDepartmentService.Add(hrDepartment);
    }

    @PutMapping(path = "")
    ResponseEntity<?> Update(@RequestBody HRDepartment hrDepartment) {
        return hrDepartmentService.Update(hrDepartment);
    }

    @PostMapping(path = "/{page}/{page_size}")
    ResponseEntity<?> Fetch(@PathVariable int page, @PathVariable int page_size, @RequestParam String department_name) throws JSONException {
        return hrDepartmentService.Search(page, page_size, department_name);
    }


}

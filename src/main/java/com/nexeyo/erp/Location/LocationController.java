package com.nexeyo.erp.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("")
    public ResponseEntity<?> Add (@RequestBody Location location){
        return locationService.AddLocation(location);
    }

    @PutMapping
    public ResponseEntity<?> updateLocation(@RequestBody Location location) {
        return locationService.UpdateLocation(location);
    }

    @GetMapping("/{page}/{page_size}/{order}")
    public ResponseEntity<?> getLocations(@PathVariable int page, @PathVariable int pageSize, @PathVariable int order) {
        return locationService.Get(page, pageSize, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable int id) {
        return locationService.GetById(id);
    }
}

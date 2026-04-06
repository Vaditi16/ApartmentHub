//package com.example.demo.controller;
//
//public class ResidentController {
//
//}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Resident;
import com.example.demo.service.ResidentService;

@RestController
@RequestMapping("/residents")
@CrossOrigin("*")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    // Add resident
    @PostMapping
    public Resident addResident(@RequestBody Resident resident) {
        return residentService.saveResident(resident);
    }

    // Get all residents
    @GetMapping
    public List<Resident> getResidents() {
        return residentService.getAllResidents();
    }

    // Delete resident
    @DeleteMapping("/{id}")
    public void deleteResident(@PathVariable Long id) {
        residentService.deleteResident(id);
    }
}
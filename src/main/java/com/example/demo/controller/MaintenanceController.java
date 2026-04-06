//package com.example.demo.controller;
//
//public class MaintenanceController {
//
//}

package com.example.demo.controller;

import com.example.demo.model.Maintenance;
import com.example.demo.service.MaintenanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@CrossOrigin("*")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    // Add maintenance for a flat
    @PostMapping("/flat/{flatId}")
    public Maintenance addMaintenance(@PathVariable Long flatId,
                                      @RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(flatId, maintenance);
    }

    // Get maintenance for a flat
    @GetMapping("/flat/{flatId}")
    public List<Maintenance> getByFlat(@PathVariable Long flatId) {
        return maintenanceService.getMaintenanceByFlat(flatId);
    }

    // Mark as paid
    @PutMapping("/pay/{id}")
    public Maintenance pay(@PathVariable Long id) {
        return maintenanceService.markAsPaid(id);
    }

    // Admin → get all
    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAll();
    }
}
//package com.example.demo.service;
//
//public class MaintenanceService {
//
//}

package com.example.demo.service;

import com.example.demo.model.Flat;
import com.example.demo.model.Maintenance;
import com.example.demo.repository.MaintenanceRepository;
import com.example.demo.repository.FlatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private FlatRepository flatRepository;

    // Add Maintenance (Admin)
    public Maintenance addMaintenance(Long flatId, Maintenance maintenance) {
        Flat flat = flatRepository.findById(flatId)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        maintenance.setFlat(flat);
        maintenance.setStatus("UNPAID");

        return maintenanceRepository.save(maintenance);
    }

    // Get by Flat (Resident view)
    public List<Maintenance> getMaintenanceByFlat(Long flatId) {
        return maintenanceRepository.findByFlatId(flatId);
    }

    // Mark as Paid
    public Maintenance markAsPaid(Long id) {
        Maintenance m = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));

        m.setStatus("PAID");
        return maintenanceRepository.save(m);
    }

    // Get All (Admin)
    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }
}
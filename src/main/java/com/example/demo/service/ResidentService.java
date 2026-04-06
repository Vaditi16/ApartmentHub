package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flat;
import com.example.demo.model.Resident;
import com.example.demo.repository.FlatRepository;
import com.example.demo.repository.ResidentRepository;

//@Service
//public class ResidentService {
//
//    @Autowired
//    private ResidentRepository residentRepository;
//
//    @Autowired
//    private FlatRepository flatRepository;
//
//    // Add resident
//    public Resident saveResident(Resident resident) {
//
//        Long flatId = resident.getFlat().getId();
//
//        Flat flat = flatRepository.findById(flatId).orElse(null);
//
//        resident.setFlat(flat);
//
//        return residentRepository.save(resident);
//    }
//
//    // Get all residents
//    public List<Resident> getAllResidents() {
//        return residentRepository.findAll();
//    }
//
//    // Delete resident
//    public void deleteResident(Long id) {
//        residentRepository.deleteById(id);
//    }
//}

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private FlatRepository flatRepository;

    public Resident saveResident(Resident resident) {

        if (resident.getFlat() != null) {

            Long flatId = resident.getFlat().getId();

            Flat flat = flatRepository.findById(flatId).orElse(null);

            resident.setFlat(flat);
        }

        return residentRepository.save(resident);
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
    }
}
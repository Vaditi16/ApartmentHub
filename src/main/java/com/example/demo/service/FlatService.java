//package com.example.demo.service;
//
//public class FlatService {
//
//}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flat;
import com.example.demo.repository.FlatRepository;

@Service
public class FlatService {

    @Autowired
    private FlatRepository flatRepository;

    // Add Flat
    public Flat saveFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    // Get all Flats
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    // Delete Flat
    public void deleteFlat(Long id) {
        flatRepository.deleteById(id);
    }
}
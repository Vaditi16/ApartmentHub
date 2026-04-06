//package com.example.demo.controller;
//
//public class FlatController {
//
//}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Flat;
import com.example.demo.service.FlatService;

@RestController
@RequestMapping("/flats")
@CrossOrigin("*")
public class FlatController {

    @Autowired
    private FlatService flatService;

    // Add Flat
    @PostMapping
    public Flat addFlat(@RequestBody Flat flat) {
        return flatService.saveFlat(flat);
    }

    // Get All Flats
    @GetMapping
    public List<Flat> getFlats() {
        return flatService.getAllFlats();
    }

    // Delete Flat
    @DeleteMapping("/{id}")
    public void deleteFlat(@PathVariable Long id) {
        flatService.deleteFlat(id);
    }
    @GetMapping("/admin-view")
    public List<com.example.demo.dto.FlatResponse> getFlatsForAdmin() {

        List<Flat> flats = flatService.getAllFlats();

        List<com.example.demo.dto.FlatResponse> responseList = new java.util.ArrayList<>();

        for (Flat flat : flats) {

            com.example.demo.dto.FlatResponse res = new com.example.demo.dto.FlatResponse();

            res.setFlatNumber(flat.getFlatNumber());
            res.setBlock(flat.getBlock());
            res.setOwnerName(flat.getOwnerName());
            res.setPhoneNumber(flat.getPhoneNumber());

            if (flat.getResidents() == null || flat.getResidents().isEmpty()) {
                res.setResidentNames("-");
            } else {
                String names = flat.getResidents()
                        .stream()
                        .map(r -> r.getName())
                        .collect(java.util.stream.Collectors.joining(", "));

                res.setResidentNames(names);
            }

            responseList.add(res);
        }

        return responseList;
    }
}
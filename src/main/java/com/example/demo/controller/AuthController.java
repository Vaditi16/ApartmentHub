//package com.example.demo.controller;
//
//public class AuthController {
//
//}
package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Flat;
import com.example.demo.model.Resident;
import com.example.demo.repository.FlatRepository;
import com.example.demo.repository.ResidentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private ResidentRepository residentRepository;

    // 🔐 LOGIN API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // 🟢 ADMIN LOGIN
        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            return ResponseEntity.ok("Admin Login Success");
        }

        // 🟢 RESIDENT LOGIN
        if ("RESIDENT".equalsIgnoreCase(request.getRole())) {

            Optional<Flat> flatOpt =
                    flatRepository.findByFlatNumber(request.getFlatNumber());

            // ❌ Flat not found
            if (flatOpt.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Flat not yet added. Contact Secretary");
            }

            Flat flat = flatOpt.get();

            // Get all residents in that flat
            List<Resident> residents = residentRepository.findByFlat(flat);

            // Check if resident already exists
            for (Resident r : residents) {
                if (r.getName().equalsIgnoreCase(request.getName())) {
                    return ResponseEntity.ok(r); // existing login
                }
            }

            // 🆕 First time login → create resident
            Resident newResident = new Resident();
            newResident.setName(request.getName());
            newResident.setFlat(flat);
            newResident.setProfileCompleted(false);

            residentRepository.save(newResident);

            return ResponseEntity.ok(newResident);
        }

        return ResponseEntity.badRequest().body("Invalid role");
    }

    // 🧾 PROFILE COMPLETION API
    @PutMapping("/complete-profile/{id}")
    public Resident completeProfile(@PathVariable Long id,
                                    @RequestBody Resident updated) {

        Resident r = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        r.setPhoneNumber(updated.getPhoneNumber());
        r.setEmail(updated.getEmail());
        r.setProfileCompleted(true);

        return residentRepository.save(r);
    }
}
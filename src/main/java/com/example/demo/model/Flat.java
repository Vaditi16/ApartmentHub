//
//
//package com.example.demo.model;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class Flat {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String flatNumber;
//    private String block;
//    private String ownerName;
//    private String phoneNumber;
//
//    @OneToMany(mappedBy = "flat")
//    private List<Resident> residents;
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getFlatNumber() {
//        return flatNumber;
//    }
//
//    public void setFlatNumber(String flatNumber) {
//        this.flatNumber = flatNumber;
//    }
//
//    public String getBlock() {
//        return block;
//    }
//
//    public void setBlock(String block) {
//        this.block = block;
//    }
//
//    public String getOwnerName() {
//        return ownerName;
//    }
//
//    public void setOwnerName(String ownerName) {
//        this.ownerName = ownerName;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//}


package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // ✅ added (important for login validation)
    private String flatNumber;

    private String block;
    private String ownerName;
    private String phoneNumber;
//
//    @OneToMany(mappedBy = "flat")
//    private List<Resident> residents;
    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "flat")
    private List<Resident> residents;

    // ✅ getters

    public Long getId() {
        return id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getBlock() {
        return block;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Resident> getResidents() { // ✅ added
        return residents;
    }

    // ✅ setters

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
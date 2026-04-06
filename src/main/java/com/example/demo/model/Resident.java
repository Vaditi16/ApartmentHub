////package com.example.demo.model;
////
////import jakarta.persistence.*;
////
////@Entity
////public class Resident {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    private String name;
////    private String phoneNumber;
////    private String email;
////    private String flatNumber;
////
////    public Long getId() {
////        return id;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public String getPhoneNumber() {
////        return phoneNumber;
////    }
////
////    public void setPhoneNumber(String phoneNumber) {
////        this.phoneNumber = phoneNumber;
////    }
////
////    public String getEmail() {
////        return email;
////    }
////
////    public void setEmail(String email) {
////        this.email = email;
////    }
////
////    public String getFlatNumber() {
////        return flatNumber;
////    }
////
////    public void setFlatNumber(String flatNumber) {
////        this.flatNumber = flatNumber;
////    }
////}
//
////package com.example.demo.model;
////
////import jakarta.persistence.*;
////import java.util.List;
////import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
////
////@JsonIgnoreProperties({"flat","phoneNumber","email"})
////@Entity
////public class Resident {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    private String name;
////    private String phoneNumber;
////    private String email;
////
////    @ManyToOne
////    @JoinColumn(name = "flat_id")
////    private Flat flat;
////
////    @OneToMany(mappedBy = "resident")
////    private List<Issue> issues;
////
////    public Long getId() {
////        return id;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public String getPhoneNumber() {
////        return phoneNumber;
////    }
////
////    public void setPhoneNumber(String phoneNumber) {
////        this.phoneNumber = phoneNumber;
////    }
////
////    public String getEmail() {
////        return email;
////    }
////
////    public void setEmail(String email) {
////        this.email = email;
////    }
////
////    public Flat getFlat() {
////        return flat;
////    }
////
////    public void setFlat(Flat flat) {
////        this.flat = flat;
////    }
////}
//
//
//package com.example.demo.model;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class Resident {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String phoneNumber;
//    private String email;
//
//    @ManyToOne
//    @JoinColumn(name = "flat_id")
//    private Flat flat;
//
//    @OneToMany(mappedBy = "resident")
//    @com.fasterxml.jackson.annotation.JsonIgnore
//    private List<Issue> issues;
//    // GETTERS AND SETTERS
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Flat getFlat() {
//        return flat;
//    }
//
//    public void setFlat(Flat flat) {
//        this.flat = flat;
//    }
//
//    public List<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(List<Issue> issues) {
//        this.issues = issues;
//    }
//}

package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;

    private boolean profileCompleted; // ✅ added

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @OneToMany(mappedBy = "resident")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Issue> issues;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isProfileCompleted() { // ✅ added
        return profileCompleted;
    }

    public void setProfileCompleted(boolean profileCompleted) { // ✅ added
        this.profileCompleted = profileCompleted;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
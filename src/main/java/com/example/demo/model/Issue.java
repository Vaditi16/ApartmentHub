//package com.example.demo.model;
//
//public class Issue {
//
//}

//package com.example.demo.model;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Issue {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String residentName;
//    private String flatNumber;
//    private String description;
//    private String category;
//    private String status;
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getResidentName() {
//        return residentName;
//    }
//
//    public void setResidentName(String residentName) {
//        this.residentName = residentName;
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
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//}


package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String category;
    private String status;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
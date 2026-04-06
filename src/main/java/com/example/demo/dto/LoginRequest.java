//package com.example.demo.dto;
//
//public class LoginRequest {
//
//}

package com.example.demo.dto;

public class LoginRequest {

    private String role;       // ADMIN or RESIDENT
    private String name;       // for resident
    private String flatNumber; // for resident

    // GETTERS

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    // SETTERS

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}
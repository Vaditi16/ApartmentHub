//package com.example.demo.dto;
//
//public class FlatResponse {
//
//}

package com.example.demo.dto;

public class FlatResponse {

    private String flatNumber;
    private String block;
    private String ownerName;
    private String phoneNumber;
    private String residentNames;

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidentNames() {
        return residentNames;
    }

    public void setResidentNames(String residentNames) {
        this.residentNames = residentNames;
    }
}
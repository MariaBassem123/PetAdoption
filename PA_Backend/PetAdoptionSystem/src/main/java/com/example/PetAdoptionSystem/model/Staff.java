package com.example.PetAdoptionSystem.model;

public class Staff {
    int staffId;
    int shelterId;
    String name;
    String email;
    String phone_number;
    int role;

    public Staff(int staffId, int shelterId, String name, String email, String phone_number, int role) {
        this.staffId = staffId;
        this.shelterId = shelterId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }
    public Staff(int shelterId, String name, String email, String phone_number, int role) {
        this.shelterId = shelterId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }
    public Staff(){}
    public int getStaffId() {
        return staffId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", shelterId=" + shelterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", role=" + role +
                '}';
    }
}
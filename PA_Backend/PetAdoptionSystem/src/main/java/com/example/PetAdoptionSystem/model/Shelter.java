package com.example.PetAdoptionSystem.model;

public class Shelter {
    int shelterId;
    String name;
    String email;
    String phone_number;
    String location;

    public Shelter(int shelterId, String name, String email, String phone_number, String location) {
        this.shelterId = shelterId;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.location = location;
    }

    public Shelter(){
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "shelterId=" + shelterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

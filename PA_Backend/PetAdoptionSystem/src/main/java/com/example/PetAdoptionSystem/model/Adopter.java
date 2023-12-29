package com.example.PetAdoptionSystem.model;

public class Adopter {
    private int adopterId;
    private String name;
    private String email;
    private  String password;
    private String phoneNumber;

    public Adopter() {
    }

    public Adopter(int adopterId, String name, String email, String password, String phoneNumber) {
        this.adopterId = adopterId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getAdopterId() {
        return adopterId;
    }

    public String getEmail() {
        return email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "adopterId=" + adopterId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

//    CREATE TABLE Adopter (
//      adopterId INT PRIMARY KEY AUTO_INCREMENT,
//      name VARCHAR(50) NOT NULL,
//      email VARCHAR(80) NOT NULL unique,
//      password VARCHAR(25) NOT NULL,
//      phoneNumber VARCHAR(11)
//    );

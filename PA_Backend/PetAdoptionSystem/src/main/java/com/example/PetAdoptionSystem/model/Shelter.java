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

/*
shelterCREATE DATABASE pet_adoption_system;

CREATE TABLE Shelter (
    shelterId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(80) NOT NULL,
    phone_number VARCHAR(11) NOT NULL,
    location VARCHAR(255),
);

INSERT INTO Shelter (name, email, phone_number, location)
VALUES
    ('Shelter 1', 'shelter1@example.com', '12345678901', 'Location 1'),
    ('Shelter 2', 'shelter2@example.com', '23456789012', 'Location 2'),
    ('Shelter 3', 'shelter3@example.com', '34567890123', 'Location 3');
 */

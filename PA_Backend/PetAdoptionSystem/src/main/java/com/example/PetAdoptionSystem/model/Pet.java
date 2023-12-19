package com.example.PetAdoptionSystem.model;

import java.sql.Date;

public class Pet {
    private int petId;
    private int shelterId;
    private String name;
    private Date birthDate;
    private boolean gender;

    private String species;

    private String breed;

    private String description;

    private String behaviour;

    private String healthStatus;

    public Pet() {
    }

    public Pet(int petId, int shelterId, String name, Date birthDate, boolean gender, String species, String breed, String description, String behaviour, String healthStatus) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
        this.description = description;
        this.behaviour = behaviour;
        this.healthStatus = healthStatus;
    }

    public int getPetId() {
        return petId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
}


//    CREATE TABLE Pet (
//        petId INT AUTO_INCREMENT,
//        shelterId INT,
//        name VARCHAR(25) NOT NULL,
//    birthDate DATE NOT NULL,
//    gender BOOLEAN NOT NULL,
//    species VARCHAR(30) NOT NULL,
//    breed VARCHAR(30) NOT NULL,
//    description VARCHAR(255),
//    behaviour VARCHAR(255),
//    healthStatus VARCHAR(255),
//    FOREIGN KEY (shelterId) REFERENCES Shelter(shelterId) on delete cascade on update cascade,
//        PRIMARY KEY (petId , shelterId)
//        );

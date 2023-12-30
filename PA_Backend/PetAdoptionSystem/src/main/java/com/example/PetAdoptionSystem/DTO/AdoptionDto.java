package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Pet;

import java.sql.Date;

public class AdoptionDto {
    private Pet pet;

    private String Adopter_Name;

    private String Adopter_Phone;
    private String Adopter_Email;

    private Date date;

    public AdoptionDto() {
    }

    public AdoptionDto(Pet pet, String adopter_Name, String adopter_Phone, String adopter_Email, Date date) {
        this.pet = pet;
        Adopter_Name = adopter_Name;
        Adopter_Phone = adopter_Phone;
        Adopter_Email = adopter_Email;
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public String getAdopter_Name() {
        return Adopter_Name;
    }

    public String getAdopter_Phone() {
        return Adopter_Phone;
    }

    public String getAdopter_Email() {
        return Adopter_Email;
    }

    public Date getDate() {
        return date;
    }
}

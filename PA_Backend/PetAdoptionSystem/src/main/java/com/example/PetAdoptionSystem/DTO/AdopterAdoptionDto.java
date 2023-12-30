package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.Shelter;

import java.sql.Date;

public class AdopterAdoptionDto {

    private Pet pet;

    private Shelter shelter;

    private Date date;

    public AdopterAdoptionDto() {
    }

    public AdopterAdoptionDto(Pet pet, Shelter shelter, Date date) {
        this.pet = pet;
        this.shelter = shelter;
        this.date = date;
    }

    public Pet getPet() {
        return pet;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public Date getDate() {
        return date;
    }
}

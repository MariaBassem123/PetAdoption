package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.Shelter;

public class AdopterAdoptionDto {

    private Pet pet;

    private Shelter shelter;

    public AdopterAdoptionDto() {
    }

    public AdopterAdoptionDto(Pet pet, Shelter shelter) {
        this.pet = pet;
        this.shelter = shelter;
    }

    public Pet getPet() {
        return pet;
    }

    public Shelter getShelter() {
        return shelter;
    }
}

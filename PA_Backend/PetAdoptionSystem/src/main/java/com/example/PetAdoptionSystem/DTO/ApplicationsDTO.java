package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Pet;

public class ApplicationsDTO {
    private Pet pet;

    private Adopter adopter;

    public ApplicationsDTO() {
    }

    public ApplicationsDTO(Pet pet, Adopter adopter) {
        this.pet = pet;
        this.adopter = adopter;
    }

    public Pet getPet() {
        return pet;
    }

    public Adopter getAdopter() {
        return adopter;
    }
}

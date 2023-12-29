package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Pet;

import javax.persistence.Lob;
import java.util.List;

public class PetDto {
    private Pet pet;
    @Lob
    private List<String>  img;

    public PetDto() {
    }
    
    public PetDto(Pet pet, List<String> img) {
        this.pet = pet;
        this.img = img;
    }

    public Pet getPet() {
        return pet;
    }

    public List<String>  getImg() {
        return img;
    }

}

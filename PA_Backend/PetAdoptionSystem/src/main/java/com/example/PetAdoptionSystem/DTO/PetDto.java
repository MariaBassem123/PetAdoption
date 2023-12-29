package com.example.PetAdoptionSystem.DTO;

import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;

import javax.persistence.Lob;
import java.util.List;

public class PetDto {
    private Pet pet;
    private List<Document> documents;

    public PetDto(Pet pet, List<Document> documents) {
        this.pet = pet;
        this.documents = documents;
    }

    public Pet getPet() {
        return pet;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}


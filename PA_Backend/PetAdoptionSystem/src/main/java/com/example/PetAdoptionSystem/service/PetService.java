package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.DTO.PetDto;
import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.DocumentRepository;
import com.example.PetAdoptionSystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public long savePet(Pet pet){return petRepository.savePet(pet);}

    public List<Pet> getAllPets(){return petRepository.getAllPets();}

    public List<PetDto> getAllPetsWithImg(){
        List<Pet> pets=petRepository.getAllPets();
        return pets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PetDto convertToDTO(Pet pet) {
        List<Document> documents = documentRepository.findImgById(pet.getPetId(), pet.getShelterId());
        if (documents == null || documents.isEmpty()) {
            return new PetDto(pet, null);
        }

        return new PetDto(pet, documents);
    }


}

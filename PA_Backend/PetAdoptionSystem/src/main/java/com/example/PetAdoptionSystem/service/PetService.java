package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.DTO.PetDto;
import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.DocumentRepository;
import com.example.PetAdoptionSystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public long savePet(Pet pet) {
        return petRepository.savePet(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }

    public List<PetDto> getAllPetsWithImg() {
        List<Pet> pets = petRepository.getAllPets();
        return pets.stream()
                .map(this::convertToDTOWithImages)
                .collect(Collectors.toList());
    }

    public PetDto convertToDTOWithImages(Pet pet) {
        List<Document> documents = documentRepository.findImgById(pet.getPetId(), pet.getShelterId());
        List<String> imgDataUrls = new ArrayList<>();

        if (documents != null && !documents.isEmpty()) {
            imgDataUrls = documents.stream()
                    .map(Document::getAttachment)
                    .map(this::convertToDataUrl)
                    .collect(Collectors.toList());
        }

        return new PetDto(pet, imgDataUrls);
    }


    private String convertToDataUrl(byte[] imageBytes) {
        try {
            // Convert byte array to Base64-encoded string
            String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);

            // Construct the data URL
            return "data:image/png;base64," + base64Encoded;
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid image data)
            e.printStackTrace();
            return null; // Or throw a custom exception if needed
        }
    }
}

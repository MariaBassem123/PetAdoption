package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.DTO.AdopterAdoptionDto;
import com.example.PetAdoptionSystem.DTO.AdoptionDto;
import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.Shelter;
import com.example.PetAdoptionSystem.repository.AdopterRepository;
import com.example.PetAdoptionSystem.repository.PetRepository;
import com.example.PetAdoptionSystem.repository.adoptionRepository;
import com.example.PetAdoptionSystem.repository.shelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PetAdoptionSystem.model.Adoption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionService {
    @Autowired
    private adoptionRepository jdbcAdoptionRepository;
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdopterRepository adopterRepository;

    @Autowired
    private shelterRepository jdbcShelterRepository;

    public void save(Adoption adoption) {
        jdbcAdoptionRepository.save(adoption);
    }

    public List<Adoption> getAllAdoptions() {
        return jdbcAdoptionRepository.getAllAdoptions();
    }

    public List<AdoptionDto> getAllByShelter(int shelterId){
        List<Adoption> adoptions= jdbcAdoptionRepository.getAllByShelter(shelterId);
        return adoptions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdoptionDto convertToDTO(Adoption adoption) {
        Pet pet=petRepository.getPetById(adoption.getPetId());
        Adopter adopter=adopterRepository.getById(adoption.getAdopterId());
        return new AdoptionDto(pet, adopter.getName(),adopter.getPhoneNumber(),adopter.getEmail(),adoption.getAdoptionDate());
    }

    public List<AdopterAdoptionDto> getAllByAdopter(int AdopterId){
        System.out.println("here");
        List<Adoption> adoptions= jdbcAdoptionRepository.getAllByAdopter(AdopterId);
        return adoptions.stream()
                .map(this::convertToAdopterDTO)
                .collect(Collectors.toList());
    }

    public AdopterAdoptionDto convertToAdopterDTO(Adoption adoption) {
        Pet pet=petRepository.getPetById(adoption.getPetId());
        Shelter shelter=jdbcShelterRepository.getShelterById(adoption.getShelterId());
        return new AdopterAdoptionDto(pet,shelter);
    }

}

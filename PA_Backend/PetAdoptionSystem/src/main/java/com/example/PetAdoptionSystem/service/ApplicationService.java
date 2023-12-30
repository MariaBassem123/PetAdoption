package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.DTO.ApplicationsDTO;
import com.example.PetAdoptionSystem.DTO.PetDto;
import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Application;
import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.AdopterRepository;
import com.example.PetAdoptionSystem.repository.ApplicationRepository;
import com.example.PetAdoptionSystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdopterRepository adopterRepository;

    public void saveApplication(Application application) throws Exception {
        applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.getAllApplications();
    }

    public List<ApplicationsDTO> getAllByShelter(int shelterId){
        List<Application> applications= applicationRepository.getAllByShelter(shelterId);
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public ApplicationsDTO convertToDTO(Application application) {
        Pet pet=petRepository.getPetById(application.getPetId());
        Adopter adopter=adopterRepository.getById(application.getAdopterId());
        return new ApplicationsDTO(pet, adopter);
    }

    public void acceptApplication(int shelterId, int petId, int adopterId){
        applicationRepository.acceptApplication(shelterId,adopterId,petId);
        applicationRepository.rejectAllOtherApplications(shelterId,petId);
    }

    public void rejectApplication(int shelterId, int petId, int adopterId){
        applicationRepository.rejectApplication(shelterId,adopterId,petId);
    }
}

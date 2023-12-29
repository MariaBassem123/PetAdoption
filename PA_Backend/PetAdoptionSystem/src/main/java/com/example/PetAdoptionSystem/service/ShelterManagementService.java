package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Shelter;
import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.repository.shelterRepository;
import com.example.PetAdoptionSystem.repository.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterManagementService {
    @Autowired
    private staffRepository jdbcStaffRepository;
    @Autowired
    private shelterRepository jdbcShelterRepository;

    public List<Staff> getAllStaffsByShelterId(int shelterId) {
        return jdbcStaffRepository.getAllStaffsByShelterId(shelterId);
    }

    public Shelter getShelterById(int shelterId) {
        System.out.println("I am hereee in service");
        Shelter shelter = jdbcShelterRepository.getShelterById(shelterId);
        System.out.println(shelter);
        return shelter;
    }

    public boolean updateShelterEmail(int shelterId, String newEmail) {
        return jdbcShelterRepository.updateShelterEmail(shelterId, newEmail);
    }

    public boolean updateShelterLocation(int shelterId, String newLocation) {
        return jdbcShelterRepository.updateShelterLocation(shelterId, newLocation);
    }

    public boolean updateShelterPhoneNumber(int shelterId, String newPhoneNumber) {
        return jdbcShelterRepository.updateShelterPhoneNumber(shelterId, newPhoneNumber);
    }
}

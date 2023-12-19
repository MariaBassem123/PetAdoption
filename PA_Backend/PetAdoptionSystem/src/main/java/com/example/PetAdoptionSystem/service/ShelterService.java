package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Shelter;
import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.repository.shelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {
    @Autowired
    private shelterRepository jdbcShelterRepository;

    public void saveShelter(Shelter shelter){
        jdbcShelterRepository.save(shelter);
    }

    public List<Shelter> getAllShelters(){
        return jdbcShelterRepository.getAllShelters();
    }

    public Shelter getShelterById(int id) {
        return jdbcShelterRepository.getShelterById(id);
    }

    public Shelter getByShelterName(String name){
        return jdbcShelterRepository.getShelterByName(name);
    }
}

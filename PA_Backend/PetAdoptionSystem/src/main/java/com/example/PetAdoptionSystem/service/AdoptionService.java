package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.repository.adoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PetAdoptionSystem.model.Adoption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {
    @Autowired
    private adoptionRepository jdbcAdoptionRepository;

    public void save(Adoption adoption) {
        jdbcAdoptionRepository.save(adoption);
    }

    public List<Adoption> getAllAdoptions() {
        return jdbcAdoptionRepository.getAllAdoptions();
    }

}

package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.repository.adopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    @Autowired
    private  adopterRepository jdbcAdopterRepository;

    public void saveAdopter(Adopter adopter) {
        jdbcAdopterRepository.saveAdopter(adopter);
    }

    public List<Adopter> getAllAdopters() {
        return jdbcAdopterRepository.getAllAdopters();
    }

    public Adopter getById(int id){ return jdbcAdopterRepository.getById(id); }
}

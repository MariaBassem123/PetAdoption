package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public void savePet(Pet pet){ petRepository.savePet(pet); }
}

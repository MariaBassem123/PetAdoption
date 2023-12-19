package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.repository.JdbcAdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    private final JdbcAdopterRepository jdbcAdopterRepository;

    @Autowired
    public AdopterService(JdbcAdopterRepository jdbcAdopterRepository) {
        this.jdbcAdopterRepository = jdbcAdopterRepository;
    }

    public void saveAdopter(Adopter adopter) {
        jdbcAdopterRepository.saveAdopter(adopter);
    }

    public List<Adopter> getAllAdopters() {
        return jdbcAdopterRepository.getAllAdopters();
    }
}

package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePet(Pet pet){
        String sql = "INSERT INTO pet (shelterId, name, birthDate, gender, species, breed, description, behaviour, healthStatus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pet.getShelterId(), pet.getName(), pet.getBirthDate(), pet.getGender(),
                pet.getSpecies(), pet.getBreed(), pet.getDescription(), pet.getBehaviour(), pet.getHealthStatus());
    }

}

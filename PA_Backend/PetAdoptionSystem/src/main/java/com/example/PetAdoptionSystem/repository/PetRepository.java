package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

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

    public List<Pet> getAllPets() {
        String sql = "SELECT * FROM pet";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Pet(
                        resultSet.getInt("petId"),
                        resultSet.getInt("shelterId"),
                        resultSet.getString("name"),
                        resultSet.getDate("birthDate"),
                        resultSet.getBoolean("gender"),
                        resultSet.getString("species"),
                        resultSet.getString("breed"),
                        resultSet.getString("description"),
                        resultSet.getString("behaviour"),
                        resultSet.getString("healthStatus")
                )
        );
    }

}

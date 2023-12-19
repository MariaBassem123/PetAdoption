package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Adoption;
import com.example.PetAdoptionSystem.model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class adoptionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public adoptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Adoption adoption) {
        jdbcTemplate.update("INSERT INTO Adoption (petId, shelterId, adopterId, adoptionDate) VALUES (?, ?, ?, ?)",
                adoption.getPetId(), adoption.getShelterId(), adoption.getAdopterId(), adoption.getAdoptionDate());

    }


    public List<Adoption> getAllAdoptions() {
        try {
            return jdbcTemplate.query("SELECT * FROM Adoption", (resultSet, rowNum) ->
                    new Adoption(resultSet.getInt("petId"),
                            resultSet.getInt("shelterId"),
                            resultSet.getInt("adopterId"),
                            resultSet.getDate("adoptionDate")));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving Adoptions", e);
        }
    }
}

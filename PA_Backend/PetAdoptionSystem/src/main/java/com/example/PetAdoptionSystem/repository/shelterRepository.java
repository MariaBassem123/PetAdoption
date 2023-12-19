package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class shelterRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public shelterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Shelter shelter) {
        try {
            jdbcTemplate.update("INSERT INTO Shelter (name, email, phone_number, location) VALUES (?, ?, ?, ?)",
                    shelter.getName(), shelter.getEmail(), shelter.getPhone_number(), shelter.getLocation());
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation (e.g., display a message or log the error)
            System.err.println("Error in save: Shelter with the same name already exists.");
        }
    }


    public List<Shelter> getAllShelters() {
        return jdbcTemplate.query("SELECT * FROM Shelter", new ShelterRowMapper());
    }

    private static class ShelterRowMapper implements RowMapper<Shelter> {
        @Override
        public Shelter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Shelter shelter = new Shelter();
            shelter.setShelterId(resultSet.getInt("shelterId"));
            shelter.setName(resultSet.getString("name"));
            shelter.setEmail(resultSet.getString("email"));
            shelter.setPhone_number(resultSet.getString("phone_number"));
            shelter.setLocation(resultSet.getString("location"));
            return shelter;
        }
    }
}

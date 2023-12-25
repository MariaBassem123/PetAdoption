package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Status;
import com.example.PetAdoptionSystem.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ApplicationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Application application) throws Exception {
        try {
            jdbcTemplate.update("INSERT INTO Application (petId, shelterId, adopterId, status) VALUES (?, ?, ?, ?)",
                    application.getPetId(), application.getShelterId(), application.getAdopterId(), application.getStatus());
            System.out.println("Inserted successfully");
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation (e.g., display a message or log the error)
            System.err.println("Error in save: Application with the same name already exists.");
            throw new DataIntegrityViolationException("Error saving the Application.");
        }
    }

    public List<Application> getAllApplications() {
        try {
            return jdbcTemplate.query("SELECT * FROM Application", (resultSet, rowNum) ->
                    new Application(resultSet.getInt("petId"),
                            resultSet.getInt("shelterId"),
                            resultSet.getInt("adopterId"),
                            Status.valueOf(resultSet.getString("status"))));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving Applications", e);
        }
    }
}

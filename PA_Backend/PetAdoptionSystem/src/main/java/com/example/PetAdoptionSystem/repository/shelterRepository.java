package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class shelterRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public shelterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Shelter shelter) {
            jdbcTemplate.update("INSERT INTO Shelter (name, email, phone_number, location) VALUES (?, ?, ?, ?)",
                    shelter.getName(), shelter.getEmail(), shelter.getPhone_number(), shelter.getLocation());

    }

    public List<Shelter> getAllShelters() {
        return jdbcTemplate.query("SELECT * FROM Shelter",
                (resultSet, rowNum) -> new Shelter(resultSet.getInt("shelterId"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("phone_number"),
                                resultSet.getString("location"))
        );
    }

    public Shelter getShelterByName(String name){
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Shelter WHERE name = ?",
                    new Object[]{name}, (resultSet, rowNum) ->
                            new Shelter(resultSet.getInt("shelterId"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone_number"),
                                    resultSet.getString("location"))
            );
        } catch (EmptyResultDataAccessException e) {
            // Shelter with the given name not found
            return null;
        }
    }
    public Shelter getShelterById(int Id){
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Shelter WHERE ShelterId = ?", new Object[]{Id}, (resultSet, rowNum) ->
                            new Shelter(resultSet.getInt("shelterId"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone_number"),
                                    resultSet.getString("location"))
            );
    }

    public boolean updateShelterEmail(int shelterId, String newEmail) {
        String sql = "UPDATE Shelter SET email = ? WHERE shelterId = ?";
        int rowsAffected = jdbcTemplate.update(sql, newEmail, shelterId);
        return rowsAffected > 0;
    }

    public boolean updateShelterLocation(int shelterId, String newLocation) {
        String sql = "UPDATE Shelter SET location = ? WHERE shelterId = ?";
        int rowsAffected = jdbcTemplate.update(sql, newLocation, shelterId);
        return rowsAffected > 0;
    }

    public boolean updateShelterPhoneNumber(int shelterId, String newPhoneNumber) {
        String sql = "UPDATE Shelter SET phone_number = ? WHERE shelterId = ?";
        int rowsAffected = jdbcTemplate.update(sql, newPhoneNumber, shelterId);
        return rowsAffected > 0;
    }
}

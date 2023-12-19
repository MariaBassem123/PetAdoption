package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Adopter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class adopterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAdopter(Adopter adopter) {
        try {
            String sql = "INSERT INTO adopters (name, email, password, phoneNumber) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, adopter.getName(), adopter.getEmail(), adopter.getPassword(), adopter.getPhoneNumber());
        } catch (DataAccessException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Error saving adopter", e);
        }
    }

    public List<Adopter> getAllAdopters() {
        try {
            String sql = "SELECT * FROM adopters";
            return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                    new Adopter(resultSet.getInt("adopterId"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phoneNumber")));
        } catch (DataAccessException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Error retrieving adopters", e);
        }
    }

    public Adopter getById(int id){
        try {
            String sql = "SELECT * FROM adopters WHERE adopterId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) ->
                    new Adopter(
                            resultSet.getInt("adopterId"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phoneNumber")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Not Found");
            return null;
        } catch (DataAccessException e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Error retrieving adopter by id", e);
        }
    }
}

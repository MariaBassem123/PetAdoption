package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdopterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAdopter(Adopter adopter) {
        String sql = "INSERT INTO adopter (name, email, password, phoneNumber) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, adopter.getName(), adopter.getEmail(), adopter.getPassword(), adopter.getPhoneNumber());

    }

    public List<Adopter> getAllAdopters() {
        String sql = "SELECT * FROM adopter";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Adopter(resultSet.getInt("adopterId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")));
    }

    public Adopter getById(int id){
        String sql = "SELECT * FROM adopter WHERE adopterId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, rowNum) ->
                new Adopter(
                        resultSet.getInt("adopterId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")
                )
        );
    }

    public Adopter getAllAdopterByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM adopter WHERE email = ?", new Object[]{email}, (resultSet, rowNum) ->
                            new Adopter(resultSet.getInt("adopterId"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("password"),
                                    resultSet.getString("phoneNumber"))
            );
        } catch (EmptyResultDataAccessException e) {
            // Staff with the given id not found
            return null;
        }
    }

    public Adopter getAdopterByEmail(String email) {
        String sql = "SELECT * FROM adopter WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (resultSet, rowNum) ->
                new Adopter(
                        resultSet.getInt("adopterId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")
                )
        );
    }
}

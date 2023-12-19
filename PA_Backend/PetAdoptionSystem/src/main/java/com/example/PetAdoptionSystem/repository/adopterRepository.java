package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Adopter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdopterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveAdopter(Adopter adopter) {
        String sql = "INSERT INTO adopters (name, email, password, phoneNumber) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, adopter.getName(), adopter.getEmail(), adopter.getPassword(), adopter.getPhoneNumber());

    }

    public List<Adopter> getAllAdopters() {
        String sql = "SELECT * FROM adopters";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Adopter(resultSet.getInt("adopterId"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phoneNumber")));
    }

    public Adopter getById(int id){
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
    }
}

package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class PetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long savePet(Pet pet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO pet (shelterId, name, birthDate, gender, species, breed, description, behaviour, healthStatus) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, pet.getShelterId());
                ps.setString(2, pet.getName());
                ps.setDate(3, pet.getBirthDate());
                ps.setBoolean(4, pet.getGender());
                ps.setString(5, pet.getSpecies());
                ps.setString(6, pet.getBreed());
                ps.setString(7, pet.getDescription());
                ps.setString(8, pet.getBehaviour());
                ps.setString(9, pet.getHealthStatus());

                return ps;
            }
        }, keyHolder);
        System.out.println(keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }


    public void updatePet(Pet pet) {
        String sql = "UPDATE pet " +
                "SET name = ?, birthDate = ?, gender = ?, species = ?, breed = ?, " +
                "description = ?, behaviour = ?, healthStatus = ? " +
                "WHERE petId = ?";

        jdbcTemplate.update(sql,
                pet.getName(),
                pet.getBirthDate(),
                pet.getGender(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getDescription(),
                pet.getBehaviour(),
                pet.getHealthStatus(),
                pet.getPetId()
        );
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

    public List<Pet> getAllPetsByShelter(int shelterID){
        String sql = "SELECT * FROM pet WHERE shelterId = ?";
        return jdbcTemplate.query(sql, new Object[]{shelterID}, (resultSet, rowNum) ->
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

    public Pet getPetById(int petId) {
        String sql = "SELECT * FROM pet WHERE petId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{petId}, (resultSet, rowNum) ->
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

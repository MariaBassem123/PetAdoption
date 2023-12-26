package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class staffRepository {
    private final JdbcTemplate jdbcTemplate;

    public staffRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Staff staff) {
        jdbcTemplate.update("INSERT INTO Staff (shelterId, password , name, email, phone_number, role) VALUES (?, ?, ?, ?, ?, ?)",
                staff.getShelterId(), staff.getPassword(),staff.getName(), staff.getEmail(), staff.getPhone_number(), staff.getRole());

    }

    public List<Staff> getAllStaffs() {
        try {
            return jdbcTemplate.query("SELECT * FROM Staff", (resultSet, rowNum) ->
                    new Staff(resultSet.getInt("staffId"),
                            resultSet.getInt("shelterId"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("password"),  // Corrected order: password comes after phone_number
                            resultSet.getInt("role")));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving Staff", e);
        }
    }
//
//    public Staff getByStaffId(int Id){
//        try {
//            return jdbcTemplate.queryForObject(
//                    "SELECT * FROM Staff WHERE StaffId = ?", new Object[]{Id}, (resultSet, rowNum) ->
//                            new Staff(resultSet.getInt("staffId"),
//                                    resultSet.getInt("shelterId"),
//                                    resultSet.getString("password"),
//                                    resultSet.getString("name"),
//                                    resultSet.getString("email"),
//                                    resultSet.getString("phone_number"),
//                                    resultSet.getInt("role"))
//            );
//        } catch (EmptyResultDataAccessException e) {
//            // Staff with the given id not found
//            return null;
//        }
//    }

//    public List<Staff> getByShelterId(int Id){
//        try {
//            return jdbcTemplate.query(
//                    "SELECT * FROM Staff WHERE ShelterId = ?", new Object[]{Id}, (resultSet, rowNum) ->
//                            new Staff(resultSet.getInt("staffId"),
//                                    resultSet.getInt("shelterId"),
//                                    resultSet.getString("password"),
//                                    resultSet.getString("name"),
//                                    resultSet.getString("email"),
//                                    resultSet.getString("phone_number"),
//                                    resultSet.getInt("role"))
//            );
//        } catch (EmptyResultDataAccessException e) {
//            // Shelter with the given id not found
//            return null;
//        }
//    }
//
//    public Staff getByStaffName(String name){
//        try {
//            return jdbcTemplate.queryForObject(
//                    "SELECT * FROM Staff WHERE name = ?", new Object[]{name}, (resultSet, rowNum) ->
//                            new Staff(resultSet.getInt("staffId"),
//                                    resultSet.getInt("shelterId"),
//                                    resultSet.getString("password"),
//                                    resultSet.getString("name"),
//                                    resultSet.getString("email"),
//                                    resultSet.getString("phone_number"),
//                                    resultSet.getInt("role"))
//            );
//        } catch (EmptyResultDataAccessException e) {
//            // Staff with the given id not found
//            return null;
//        }
//    }

    public Staff getStaffByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Staff WHERE email = ?", new Object[]{email}, (resultSet, rowNum) ->
                            new Staff(
                                    resultSet.getInt("staffId"),
                                    resultSet.getInt("shelterId"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phone_number"),
                                    resultSet.getString("password"),
                                    resultSet.getInt("role"))
            );
        } catch (EmptyResultDataAccessException e) {
            // If no staff is found with the given email, return null or handle as appropriate
            return null;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error retrieving Staff by email", e);
        }
    }
}

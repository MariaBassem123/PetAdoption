package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveDocument(Document document) {
        String sql = "INSERT INTO Document (petId, shelterId, type, attachment) " +
                "VALUES ( ?, ?, ?, ?)";
        jdbcTemplate.update(sql, document.getPetId(), document.getShelterId(),
                document.getType(), document.getAttachment());

    }
}

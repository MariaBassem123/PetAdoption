package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Document> findImgById(int petId, int shelterId) {
        String sql = "SELECT * FROM document WHERE petId = ? AND shelterId = ? AND type = 'image/jpeg'";

        return jdbcTemplate.query(sql, new Object[]{petId, shelterId}, (resultSet, rowNum) ->
                new Document(resultSet.getInt("documentId"),
                        resultSet.getInt("petId"),
                        resultSet.getInt("shelterId"),
                        resultSet.getString("type"),
                        resultSet.getBytes("attachment")));
    }

}

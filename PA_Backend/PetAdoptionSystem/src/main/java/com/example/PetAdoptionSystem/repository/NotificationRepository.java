package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveNotification(Notification notification){
        String sql = "INSERT INTO Notification (adopterId, description,status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, notification.getAdopterId(), notification.getDescription(), notification.isStatus());
    }
}

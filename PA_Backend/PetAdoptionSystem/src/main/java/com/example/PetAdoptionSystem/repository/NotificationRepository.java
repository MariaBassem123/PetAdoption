package com.example.PetAdoptionSystem.repository;

import com.example.PetAdoptionSystem.model.Notification;
import com.example.PetAdoptionSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveNotification(Notification notification) {
        String sql = "INSERT INTO Notification (adopterId, description,status, notificationDate) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, notification.getAdopterId(), notification.getDescription(), notification.isStatus(), notification.getNotificationDate());
    }

    public List<Notification> getAllNotificationsByAdopterId(int adopterId) {
        String sql = "SELECT * FROM Notification WHERE adopterId = ?";
        return jdbcTemplate.query(sql, new Object[]{adopterId},
                (resultSet, rowNum) ->
                new Notification(
                    resultSet.getInt("notificationId"),
                    resultSet.getInt("adopterId"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("status"),
                    resultSet.getDate("notificationDate")
                )
        );
    }
}

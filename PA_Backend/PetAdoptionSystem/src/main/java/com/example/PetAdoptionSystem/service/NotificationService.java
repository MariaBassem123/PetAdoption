package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Notification;
import com.example.PetAdoptionSystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void saveNotification(Notification notification){
        notificationRepository.saveNotification(notification);
    }

    public List<Notification> getAllNotificationsByAdopterId(int adopterId) {
        List<Notification> notifications = notificationRepository.getAllNotificationsByAdopterId(adopterId);

        // Sort notifications by date in descending order
        notifications.sort(Comparator.comparing(Notification::getNotificationDate).reversed());

        return notifications;
    }
}

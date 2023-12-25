package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Notification;
import com.example.PetAdoptionSystem.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void saveNotification(Notification notification){ notificationRepository.saveNotification(notification); }
}
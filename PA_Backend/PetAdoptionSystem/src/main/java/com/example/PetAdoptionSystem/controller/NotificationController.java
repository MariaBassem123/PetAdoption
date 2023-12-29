package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Notification;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin()
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveNotification(@RequestBody Notification notification) {
        try {
            notificationService.saveNotification(notification);
            return ResponseEntity.status(HttpStatus.CREATED).body("Notification saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Notification");
        }
    }

    @GetMapping("/adopter/{adopterId}")
    public List<Notification> getAllNotificationsByAdopterId(@PathVariable int adopterId) {
        return notificationService.getAllNotificationsByAdopterId(adopterId);
    }
}

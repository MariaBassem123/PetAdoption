package com.example.PetAdoptionSystem.model;

public class Notification {
    private int notificationId;
    private int adopterId;
    private String description;

    public Notification() {
    }

    public Notification(int notificationId, int adopterId, String description) {
        this.notificationId = notificationId;
        this.adopterId = adopterId;
        this.description = description;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getAdopterId() {
        return adopterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

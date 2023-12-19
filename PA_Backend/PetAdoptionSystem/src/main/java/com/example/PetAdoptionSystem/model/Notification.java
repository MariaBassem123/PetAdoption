package com.example.PetAdoptionSystem.model;

public class Notification {
    private int notificationId;
    private int adopterId;
    private String description;
    private boolean status;

    public Notification() {
    }

    public Notification(int notificationId, int adopterId, String description, boolean status) {
        this.notificationId = notificationId;
        this.adopterId = adopterId;
        this.description = description;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

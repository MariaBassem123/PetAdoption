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


//    CREATE TABLE Notification (
//        notificationId INT auto_increment,
//        adopterId INT,
//        description VARCHAR(255),
//    status BOOLEAN NOT NULL,
//    FOREIGN KEY (adopterId) REFERENCES Adopter(adopterId) on delete cascade on update cascade,
//        primary key(notificationId, adopterId)
//        );

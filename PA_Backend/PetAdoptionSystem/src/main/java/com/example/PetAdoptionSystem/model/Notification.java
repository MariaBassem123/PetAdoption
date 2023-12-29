package com.example.PetAdoptionSystem.model;

import java.sql.Date;

public class Notification {
    private int notificationId;
    private int adopterId;
    private String description;
    private boolean status;
    private Date notificationDate;

    public Notification() {
    }

    public Notification(int notificationId, int adopterId, String description, boolean status, Date notificationDate) {
        this.notificationId = notificationId;
        this.adopterId = adopterId;
        this.description = description;
        this.status = status;
        this.notificationDate = notificationDate;
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

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }
}

/*
CREATE TABLE Notification (
	notificationId INT auto_increment,
	adopterId INT,
	description VARCHAR(255),
	status BOOLEAN NOT NULL,
    notificationDate DATE NOT NULL,
	FOREIGN KEY (adopterId) REFERENCES Adopter(adopterId) on delete cascade on update cascade, primary key(notificationId, adopterId)
);
 */

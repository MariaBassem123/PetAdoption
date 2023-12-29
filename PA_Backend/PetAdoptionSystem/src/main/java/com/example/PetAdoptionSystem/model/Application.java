package com.example.PetAdoptionSystem.model;

public class Application {
    private int petId;
    private int shelterId;
    private int adopterId;
    private Status status;

    public Application(int petId, int shelterId, int adopterId, Status status) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.adopterId = adopterId;
        this.status = status;
    }

    public int getPetId() {
        return petId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public int getAdopterId() {
        return adopterId;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /*
    CREATE TABLE Application (
    petId INT,
    shelterId INT,
    adopterId INT,
    status ENUM('APPROVED', 'WAITING', 'REJECTED'),
    PRIMARY KEY (petId, shelterId, adopterId),
    CONSTRAINT fk_pet_application FOREIGN KEY (petId) REFERENCES Pet(petId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_shelter_application FOREIGN KEY (shelterId) REFERENCES Pet(shelterId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_adopter_application FOREIGN KEY (adopterId) REFERENCES Adopter(adopterId) ON DELETE CASCADE ON UPDATE CASCADE
    );
    drop table Application;
    */
}

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
}

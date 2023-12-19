package com.example.PetAdoptionSystem.model;

import java.sql.Date;
public class Adoption {
    private int petId;
    private int shelterId;
    private int adopterId;
    private Date adoptionDate;

    public Adoption(int petId, int shelterId, int adopterId, Date adoptionDate) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.adopterId = adopterId;
        this.adoptionDate = adoptionDate;
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

    public Date getAdoptionDate() {
        return adoptionDate;
    }
}
/*
CREATE TABLE Adoption (
    petId INT,
    shelterId INT,
    adopterId INT,
    adoptionDate DATE,
    PRIMARY KEY (petId, shelterId, adopterId),
    CONSTRAINT fk_pet_adoption FOREIGN KEY (petId) REFERENCES Pet(petId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_shelter_adoption FOREIGN KEY (shelterId) REFERENCES Pet(shelterId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_adopter_adoption FOREIGN KEY (adopterId) REFERENCES Adopter(adopterId) ON DELETE CASCADE ON UPDATE CASCADE
);
 */

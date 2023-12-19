package com.example.PetAdoptionSystem.model;
import javax.persistence.Lob;
import java.sql.Blob;

public class Document {

    private int documentId;
    private int petId;
    private int shelterId;
    private String type;

    @Lob
    private byte[] attachment;

    public Document() {
    }

    public Document(int documentId, int petId, int shelterId, String type, byte[] attachment) {
        this.documentId = documentId;
        this.petId = petId;
        this.shelterId = shelterId;
        this.type = type;
        this.attachment = attachment;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getPetId() {
        return petId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }


}

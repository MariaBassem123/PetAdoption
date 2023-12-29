package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(value ="/save" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveDocument(
            @RequestParam("attachment") MultipartFile file,
            @RequestParam("petId") Long petId,
            @RequestParam("type") String type,
            @RequestParam("shelterId") Long shelterId) {

        try {
            Document document = new Document();
            document.setPetId(Math.toIntExact(petId));
            document.setType(type);
            document.setAttachment(file.getBytes());
            document.setShelterId(Math.toIntExact(shelterId));

            documentService.saveDocument(document);

            return ResponseEntity.status(HttpStatus.CREATED).body("Document saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving document");
        }
    }


}

package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/save")
    public ResponseEntity<String> saveDocument(@RequestBody Document document) {
        try {
            documentService.saveDocument(document);
            return ResponseEntity.status(HttpStatus.CREATED).body("Document saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving document");
        }
    }
}

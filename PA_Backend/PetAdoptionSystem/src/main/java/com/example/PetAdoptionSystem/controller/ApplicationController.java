package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Application;
import com.example.PetAdoptionSystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
@CrossOrigin()
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveApplication(@RequestBody Application application) {
        try {
            applicationService.saveApplication(application);
            return ResponseEntity.status(HttpStatus.CREATED).body("Application saved successfully");
        }catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Application not saved successfully. Integrity constraints violated.");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving application");
        }
    }
}

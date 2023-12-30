package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.DTO.ApplicationsDTO;
import com.example.PetAdoptionSystem.model.Application;
import com.example.PetAdoptionSystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllByShelter")
    public ResponseEntity<List<ApplicationsDTO>> getAllByShelter(@RequestParam int shelterID) {
        try {
            List<ApplicationsDTO> applications= applicationService.getAllByShelter(shelterID);
            return ResponseEntity.status(HttpStatus.CREATED).body(applications);
        }catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/acceptApplication")
    public ResponseEntity<String> acceptApplication(@RequestParam int shelterID,@RequestParam int adopterID,
                                                    @RequestParam int petID) {
        try {
            applicationService.acceptApplication(shelterID,adopterID,petID);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failure with data integrity");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PutMapping("/rejectApplication")
    public ResponseEntity<String> rejectApplication(@RequestParam int shelterID,@RequestParam int adopterID,
                                                    @RequestParam int petID) {
        try {
            applicationService.rejectApplication(shelterID,adopterID,petID);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }catch (DataIntegrityViolationException d) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failure with data integrity");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
}

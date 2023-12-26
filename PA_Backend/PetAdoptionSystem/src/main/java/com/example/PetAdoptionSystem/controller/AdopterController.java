package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.service.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopters")
@CrossOrigin()
public class AdopterController {

    @Autowired
    private AdopterService adopterService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAdopter(@RequestBody Adopter adopter) {
        try {
            adopterService.saveAdopter(adopter);
            return ResponseEntity.status(HttpStatus.CREATED).body("Adopter saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Adopter>> getAllAdopters() {
        try {
            List<Adopter> adopters = adopterService.getAllAdopters();
            return ResponseEntity.ok(adopters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Adopter> getbyId(@RequestParam int id) {
        try {
            Adopter adopter = adopterService.getById(id);
            if (adopter != null) {
                return ResponseEntity.ok(adopter);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

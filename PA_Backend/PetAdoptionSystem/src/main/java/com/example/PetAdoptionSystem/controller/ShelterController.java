package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Shelter;
import com.example.PetAdoptionSystem.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shelter")
@CrossOrigin()
public class ShelterController {
    @Autowired
    private ShelterService shelterService;

    @PostMapping("/save")
    public ResponseEntity<String> saveShelter(@RequestBody Shelter shelter) {
        try {
            shelterService.saveShelter(shelter);
            return ResponseEntity.status(HttpStatus.CREATED).body("Shelter saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving shelter");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Shelter>> getAllAShelters() {
        try {
            List<Shelter> shelters = shelterService.getAllShelters();
            System.out.println(shelters);
            return ResponseEntity.ok(shelters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Shelter> getById(@RequestParam int id) {
        try {
            Shelter shelter = shelterService.getShelterById(id);
            if (shelter != null) {
                return ResponseEntity.ok(shelter);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<Shelter> getByShelterName(@RequestParam String name) {
        try {
            Shelter shelter = shelterService.getByShelterName(name);
            if (shelter != null) {
                return ResponseEntity.ok(shelter);
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

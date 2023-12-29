package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.DTO.PetDto;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@CrossOrigin()
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/save")
    public ResponseEntity<String> savePet(@RequestBody Pet pet) {
        try {
            petService.savePet(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pet saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving pet");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PetDto>> getAllPets() {
        try {
            List<PetDto> pets = petService.getAllPetsWithImg();
            return ResponseEntity.ok(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

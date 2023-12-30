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
            long petId = petService.savePet(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(petId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving pet");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePet(@RequestBody Pet pet) {
        try {
           petService.updatePet(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body("Updated successfully");
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

    @GetMapping("/getAllByShelter")
    public ResponseEntity<List<PetDto>> getAllByShelter(@RequestParam int shelterID) {
        try {
            List<PetDto> pets = petService.getAllPetsWithImgByShelter(shelterID);
            return ResponseEntity.ok(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Shelter;
import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.service.ShelterManagementService;
import com.example.PetAdoptionSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manageShelter")
@CrossOrigin()
public class ShelterManagementController {

    @Autowired
    private ShelterManagementService shelterManagementService;

    @GetMapping("/getAllStaffs/{shelterId}")
    public ResponseEntity<List<Staff>> getAllStaffsByShelterId(@PathVariable int shelterId) {
        try {
            List<Staff> staffList = shelterManagementService.getAllStaffsByShelterId(shelterId);
            return ResponseEntity.ok(staffList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getShelter/{shelterId}")
    public ResponseEntity<Shelter> getShelterByShelterId(@PathVariable int shelterId) {
        try {
            Shelter shelter = shelterManagementService.getShelterById(shelterId);
            return ResponseEntity.ok(shelter);
        } catch (Exception e) {
            System.out.println("In error in controller");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateShelterEmail/{shelterId}")
    public ResponseEntity<Boolean> updateShelterEmail(@PathVariable int shelterId, @RequestParam String newEmail) {
        boolean updated = shelterManagementService.updateShelterEmail(shelterId, newEmail);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/updateShelterLocation/{shelterId}")
    public ResponseEntity<Boolean> updateShelterLocation(@PathVariable int shelterId, @RequestParam String newLocation) {
        boolean updated = shelterManagementService.updateShelterLocation(shelterId, newLocation);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/updateShelterPhoneNumber/{shelterId}")
    public ResponseEntity<Boolean> updateShelterPhoneNumber(@PathVariable int shelterId, @RequestParam String newPhoneNumber) {
        boolean updated = shelterManagementService.updateShelterPhoneNumber(shelterId, newPhoneNumber);
        return ResponseEntity.ok(updated);
    }
}

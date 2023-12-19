package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.service.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopters")
public class AdopterController {

    @Autowired
    private AdopterService adopterService;

    @PostMapping("/save")
    public void saveAdopter(@RequestBody Adopter adopter) {
        adopterService.saveAdopter(adopter);
    }

    @GetMapping("/getAll")
    public List<Adopter> getAllAdopters() {
        return adopterService.getAllAdopters();
    }
}

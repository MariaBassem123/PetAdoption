package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manageShelter")
@CrossOrigin()
public class ShelterManagementController {

    @Autowired
    private StaffService staffService;

    

}

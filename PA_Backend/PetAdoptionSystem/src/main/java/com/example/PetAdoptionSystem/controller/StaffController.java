package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
@CrossOrigin()
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    public ResponseEntity<String> saveStaff(@RequestBody Staff staff) {
        try {
            System.out.println(staff);
            staffService.saveStaff(staff);
            return ResponseEntity.status(HttpStatus.CREATED).body("Staff saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Staff>> getAllAdopters() {
        try {
            List<Staff> staffs = staffService.getAllStaffs();
            return ResponseEntity.ok(staffs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/getByStaffId")
//    public ResponseEntity<Staff> getByStaffId(@RequestParam int id) {
//        try {
//            Staff staff = staffService.getByStaffId(id);
//            if (staff != null) {
//                return ResponseEntity.ok(staff);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (EmptyResultDataAccessException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @GetMapping("/getByShelterId")
//    public ResponseEntity<List<Staff>> getByShelterId(@RequestParam int id) {
//        try {
//            List<Staff> staffs = staffService.getByShelterId(id);
//            return ResponseEntity.ok(staffs);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @GetMapping("/getByName")
//    public ResponseEntity<Staff> getByStaffName(@RequestParam String name) {
//        try {
//            Staff staff = staffService.getByStaffName(name);
//            if (staff != null) {
//                return ResponseEntity.ok(staff);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (EmptyResultDataAccessException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @GetMapping("/getStaffByEmail")
    public Staff getStaffByEmail(@RequestParam String email, @RequestParam String password) {
        return staffService.getStaff(email, password);
    }

    @GetMapping("/checkStaff")
    public ResponseEntity<String> checkStaff(@RequestParam String email, @RequestParam String password) {
        System.out.println("In controller: email = "+ email + ", password = " + password);
        StaffService.LoginStatus loginStatus = staffService.checkStaff(email, password);
        return ResponseEntity.ok(loginStatus.name());
    }
}

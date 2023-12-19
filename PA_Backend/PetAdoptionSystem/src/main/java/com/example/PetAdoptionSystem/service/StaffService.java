package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.repository.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private staffRepository jdbcStaffRepository;

    public void saveStaff(Staff staff){
        jdbcStaffRepository.save(staff);
    }

    public List<Staff> getAllStaffs(){
        return jdbcStaffRepository.getAllStaffs();
    }

    public Staff getByStaffId(int id) {
        return jdbcStaffRepository.getByStaffId(id);
    }

    public List<Staff> getByShelterId(int id) {
        return jdbcStaffRepository.getByShelterId(id);
    }

    public Staff getByStaffName(String name){
        return jdbcStaffRepository.getByStaffName(name);
    }

}

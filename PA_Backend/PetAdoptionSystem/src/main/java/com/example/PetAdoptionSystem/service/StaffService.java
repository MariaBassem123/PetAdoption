package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Staff;
import com.example.PetAdoptionSystem.repository.staffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StaffService {
    @Autowired
    private staffRepository jdbcStaffRepository;

    public void saveStaff(Staff staff) throws Exception {
        if (!isValidPassword(staff.getPassword())) {
            throw new Exception("Invalid password. Please follow the specified constraints.");
        }else if (!isValidPhone(staff.getPhone_number())) {
            throw new Exception("Invalid phone number. Please follow the specified constraints.");
        }else if(isEmailFound(staff.getEmail())){
            throw new Exception("Email already exist.");
        }
        else {
            jdbcStaffRepository.save(staff);
        }
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
    public boolean isValidPhone(String phone) {
        return  Pattern.matches("^\\d{11}$", phone) ;
    }

    public boolean isValidPassword(String pass) {
        return Pattern.matches("^.{8,16}$", pass);
    }

    public boolean isEmailFound(String email){
        return jdbcStaffRepository.getStaffByEmail(email) !=null;
    }


}

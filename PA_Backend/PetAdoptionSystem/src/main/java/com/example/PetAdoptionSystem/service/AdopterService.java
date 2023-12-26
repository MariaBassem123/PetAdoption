package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Adopter;
import com.example.PetAdoptionSystem.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdopterService {

    @Autowired
    private AdopterRepository jdbcAdopterRepository;

    public void saveAdopter(Adopter adopter) throws Exception {

        if (!isValidPassword(adopter.getPassword())) {
            throw new Exception("Invalid password. Please follow the specified constraints.");
        }else if (!isValidPhone(adopter.getPhoneNumber())) {
            throw new Exception("Invalid phone number. Please follow the specified constraints.");
        }else if(isEmailFound(adopter.getEmail())){
            throw new Exception("Email already exist.");
        }
        else {
            jdbcAdopterRepository.saveAdopter(adopter);
        }
    }

    public List<Adopter> getAllAdopters() {
        return jdbcAdopterRepository.getAllAdopters();
    }

    public Adopter getById(int id){ return jdbcAdopterRepository.getById(id); }

    public boolean isValidPhone(String phone) {
        return  Pattern.matches("^\\d{11}$", phone) ;
    }

    public boolean isValidPassword(String pass) {
        return Pattern.matches("^.{8,16}$", pass);
    }

    public boolean isEmailFound(String email){
        return jdbcAdopterRepository.getAllAdopterByEmail(email) !=null;
    }

}

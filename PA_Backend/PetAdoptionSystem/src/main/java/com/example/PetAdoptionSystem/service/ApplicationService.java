package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Application;
import com.example.PetAdoptionSystem.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public void saveApplication(Application application) throws Exception {
        applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.getAllApplications();
    }
}

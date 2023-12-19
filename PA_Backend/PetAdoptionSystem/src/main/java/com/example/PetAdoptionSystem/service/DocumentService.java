package com.example.PetAdoptionSystem.service;

import com.example.PetAdoptionSystem.model.Document;
import com.example.PetAdoptionSystem.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public void saveDocument(Document document){documentRepository.saveDocument(document);}
}

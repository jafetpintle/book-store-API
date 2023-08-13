package com.jaf.bookstore.service;

import com.jaf.bookstore.persistence.entity.EditorialEntity;
import com.jaf.bookstore.persistence.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    public List<EditorialEntity> getAll(){
        return editorialRepository.findAll();
    }

    public EditorialEntity getById(int id){
        Optional<EditorialEntity> editorialEntityOptional = editorialRepository.findById(id);

        if(editorialEntityOptional.isPresent()){
            return editorialEntityOptional.get();
        }else{
            throw new IllegalArgumentException("");
        }
    }

    public EditorialEntity save(EditorialEntity editorial){
        return editorialRepository.save(editorial);
    }
}

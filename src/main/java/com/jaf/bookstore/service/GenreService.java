package com.jaf.bookstore.service;

import com.jaf.bookstore.persistence.entity.GenreEntity;
import com.jaf.bookstore.persistence.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> getAll(){
       return genreRepository.findAll();
    }

    public GenreEntity getById(int id){
        return genreRepository.findById(id).orElse(null);
    }

    public GenreEntity save(GenreEntity genre){
        return genreRepository.save(genre);
    }
}

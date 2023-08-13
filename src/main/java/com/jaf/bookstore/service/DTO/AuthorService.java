package com.jaf.bookstore.service.DTO;

import com.jaf.bookstore.persistence.entity.AuthorEntity;
import com.jaf.bookstore.persistence.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorEntity> getAll(){
        return authorRepository.findAll();
    }

    public AuthorEntity getById(int id){
        Optional<AuthorEntity> authorEntityOptional = authorRepository.findById(id);

        if(authorEntityOptional.isPresent()){
            return authorEntityOptional.get();
        }else{
            throw new IllegalArgumentException("Author id doesn exist.");
        }
    }

    public AuthorEntity save(AuthorEntity author){
        if(author.getIdAuthor()==null){
            return authorRepository.save(author);
        }else{
            throw new IllegalArgumentException("Error");
        }
    }

    public List<AuthorEntity> getByName(String name){
        List<AuthorEntity> authors = authorRepository.findAllByNameContainsIgnoreCase(name);

        if(authors.size() > 0){
            return authors;
        }else{
            throw new RuntimeException("Authors is empty");
        }
    }
}

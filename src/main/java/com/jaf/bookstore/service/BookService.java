package com.jaf.bookstore.service;

import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }

    public BookEntity getById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<BookEntity> getByTitle(String title){
        return bookRepository.findAllByTitleContains(title);
    }

    public List<BookEntity> getByLanguage(String language){
        return bookRepository.findAllByLanguage(language);
    }

    public BookEntity getByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }


}

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
}

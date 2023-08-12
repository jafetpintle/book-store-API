package com.jaf.bookstore.controller;

import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAll(){
        return ResponseEntity.ok(this.bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getById(@PathVariable int id){
        Optional<BookEntity> bookOptional = Optional.ofNullable(this.bookService.getById(id));

        if(bookOptional.isPresent()){
            return ResponseEntity.ok(bookOptional.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookEntity>> getByTitle(@PathVariable String title){
        List<BookEntity> books = this.bookService.getByTitle(title);

        if(books.size()>0){
            return ResponseEntity.ok(books);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

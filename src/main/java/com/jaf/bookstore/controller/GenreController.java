package com.jaf.bookstore.controller;

import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.persistence.entity.GenreEntity;
import com.jaf.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    ResponseEntity<List<GenreEntity>> getAll(){
        return ResponseEntity.ok(this.genreService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<GenreEntity> getById(@PathVariable int id){
        Optional<GenreEntity> genreEntityOptional = Optional.ofNullable(genreService.getById(id));

        return genreEntityOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    ResponseEntity<GenreEntity> createGenre(@RequestBody GenreEntity genre){
        return ResponseEntity.ok(genreService.save(genre));
    }


}

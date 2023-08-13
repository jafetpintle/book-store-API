package com.jaf.bookstore.controller;

import com.jaf.bookstore.persistence.entity.EditorialEntity;
import com.jaf.bookstore.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/editorials")
public class EditorialController {
    private final EditorialService editorialService;

    @Autowired
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping
    public ResponseEntity<List<EditorialEntity>> getAll(){
        return ResponseEntity.ok(this.editorialService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialEntity> getById(@PathVariable int id){
        try{
            return ResponseEntity.ok(this.editorialService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<EditorialEntity> save(@RequestBody EditorialEntity editorial){
        return ResponseEntity.ok(this.editorialService.save(editorial));
    }
}

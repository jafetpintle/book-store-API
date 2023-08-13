package com.jaf.bookstore.persistence.repository;

import com.jaf.bookstore.persistence.entity.AuthorEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AuthorRepository extends ListCrudRepository<AuthorEntity, Integer> {
    public List<AuthorEntity> findAllByNameContainsIgnoreCase(String name);
}

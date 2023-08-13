package com.jaf.bookstore.persistence.repository;

import com.jaf.bookstore.persistence.entity.GenreEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface GenreRepository extends ListCrudRepository<GenreEntity, Integer> {
}

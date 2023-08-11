package com.jaf.bookstore.persistence.repository;

import com.jaf.bookstore.persistence.entity.BookEntity;
import org.springframework.data.repository.ListCrudRepository;
public interface BookRepository extends ListCrudRepository<BookEntity, Integer> {
}

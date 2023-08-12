package com.jaf.bookstore.persistence.repository;

import com.jaf.bookstore.persistence.entity.BookEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BookRepository extends ListCrudRepository<BookEntity, Integer> {
    public List<BookEntity> findAllByTitleContains(String title);

    public List<BookEntity> findAllByLanguage(String language);

    public BookEntity findByIsbn(String isbn);

    public List<BookEntity> findAllByPriceBetween(double min, double max);

    public List<BookEntity> findAllByPagesLessThan(int pages);
}

package com.jaf.bookstore.persistence.repository;

import com.jaf.bookstore.persistence.entity.EditorialEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface EditorialRepository extends ListCrudRepository<EditorialEntity, Integer> {
}

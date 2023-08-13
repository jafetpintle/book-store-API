package com.jaf.bookstore.service.DTO;

import com.jaf.bookstore.persistence.entity.AuthorEntity;
import com.jaf.bookstore.persistence.entity.EditorialEntity;
import com.jaf.bookstore.persistence.entity.GenreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
public class BookUpdateDto {
    public String title;
    public LocalDate date;
    public String synopsis;
    public String language;
    public Double price;
    public Integer pages;
    public String isbn;
    public String cover;
    public List<AuthorEntity> authors;
    public GenreEntity genre;
    public EditorialEntity editorial;
}

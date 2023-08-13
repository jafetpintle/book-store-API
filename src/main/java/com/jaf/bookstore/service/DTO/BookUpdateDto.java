package com.jaf.bookstore.service.DTO;

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
    public List<AuthorDto> authors;
    public Integer genre;
    public Integer editorial;
}

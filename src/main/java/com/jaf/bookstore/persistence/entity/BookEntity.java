package com.jaf.bookstore.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Setter
@Getter
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    public Integer idBook;

    @Column(nullable = false, length = 100)
    public String title;

    public LocalDate date;

    @Column(nullable = false)
    public String synopsis;

    @Column(nullable = false, length = 15)
    public String language;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    public Double price;

    @Column(nullable = false)
    public Integer pages;

    @Column(nullable = false, length = 17)
    public String isbn;

    public String cover;

    @ManyToMany
    @JoinTable(name="books_author", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name="id_author"))
    public List<AuthorEntity> authors;

    @ManyToOne
    @JoinColumn(name = "genre", referencedColumnName = "id_genre", insertable = false, updatable = false)
    public GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "editorial", referencedColumnName = "id_editorial", insertable = false, updatable = false)
    public EditorialEntity editorial;

}

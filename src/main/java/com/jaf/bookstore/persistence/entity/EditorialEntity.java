package com.jaf.bookstore.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "editorial")
@Setter
@Getter
@NoArgsConstructor
public class EditorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial", nullable = false)
    public Integer idEditorial;

    @Column( nullable = false ,length = 30)
    public String name;

    @OneToMany(mappedBy = "editorial")
    public List<BookEntity> books;

}
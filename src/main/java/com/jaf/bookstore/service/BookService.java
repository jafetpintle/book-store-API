package com.jaf.bookstore.service;

import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.persistence.entity.EditorialEntity;
import com.jaf.bookstore.persistence.entity.GenreEntity;
import com.jaf.bookstore.persistence.repository.BookRepository;
import com.jaf.bookstore.persistence.repository.EditorialRepository;
import com.jaf.bookstore.persistence.repository.GenreRepository;
import com.jaf.bookstore.service.DTO.BookUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final EditorialService editorialService;

    @Autowired
    public BookService(BookRepository bookRepository, GenreService genreService, EditorialService editorialService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.editorialService = editorialService;
    }




    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }

    public BookEntity getById(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<BookEntity> getByTitle(String title){
        return bookRepository.findAllByTitleContains(title);
    }

    public List<BookEntity> getByLanguage(String language){
        return bookRepository.findAllByLanguage(language);
    }

    public BookEntity getByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<BookEntity> getByPriceRange(double min, double max){
        return bookRepository.findAllByPriceBetween(min, max);
    }

    public List<BookEntity> getByPagesMax(int pages){
        return bookRepository.findAllByPagesLessThan(pages);
    }

    public List<BookEntity> getByGenre(String genre){
        return bookRepository.findAllByGenreName(genre);
    }

    public List<BookEntity> getByEditorial(String editorial){ return bookRepository.findAllByEditorialNameContainsIgnoreCase(editorial); }

    public List<BookEntity> getByAuthor(String author){
        return bookRepository.findAllByAuthorsNameIgnoreCase(author);
    }

    public void updateBook(int id, BookUpdateDto bookUpdateDto){
        Optional<BookEntity> bookOptional = bookRepository.findById(id);

        if(bookOptional.isPresent()){
            BookEntity book = bookOptional.get();

            if(bookUpdateDto.getTitle()!=null){
                book.setTitle(bookUpdateDto.getTitle());
            }
            if(bookUpdateDto.getDate()!=null){
                book.setDate(bookUpdateDto.getDate());
            }if(bookUpdateDto.getIsbn()!=null){
                book.setIsbn(bookUpdateDto.getIsbn());
            }if(bookUpdateDto.getCover()!=null){
                book.setCover(bookUpdateDto.getCover());
            }if(bookUpdateDto.getLanguage()!=null){
                book.setLanguage(bookUpdateDto.getLanguage());
            }if(bookUpdateDto.getSynopsis()!=null){
                book.setSynopsis(bookUpdateDto.getSynopsis());
            }if(bookUpdateDto.getPrice()!=null){
                book.setPrice(bookUpdateDto.getPrice());
            }if(bookUpdateDto.getPages()!=null){
                book.setPages(bookUpdateDto.getPages());
            }if(bookUpdateDto.getGenre()!=null){
                Optional<GenreEntity> newGenre = Optional.ofNullable(genreService.getById(bookUpdateDto.getGenre().getIdGenre()));
                if (newGenre.isPresent()){
                    book.setGenre(newGenre.get());
                }else{
                    throw new IllegalArgumentException("Genre id doesnt exist");
                }

            }if(bookUpdateDto.getEditorial()!=null){
                Optional<EditorialEntity> newEditorial = Optional.ofNullable(editorialService.getById((bookUpdateDto.getEditorial().getIdEditorial())));
                if (newEditorial.isPresent()){
                    book.setEditorial(newEditorial.get());
                }else{
                    throw new IllegalArgumentException("Editorial id doesnt exist");
                }
            }

            bookRepository.save(book);
        }
    }

    public boolean exist(int id){
        return bookRepository.existsById(id);
    }


}

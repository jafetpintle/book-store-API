package com.jaf.bookstore.service;

import com.jaf.bookstore.persistence.entity.AuthorEntity;
import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.persistence.entity.EditorialEntity;
import com.jaf.bookstore.persistence.entity.GenreEntity;
import com.jaf.bookstore.persistence.repository.BookRepository;
import com.jaf.bookstore.service.DTO.AuthorDto;
import com.jaf.bookstore.service.DTO.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final EditorialService editorialService;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, GenreService genreService, EditorialService editorialService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.editorialService = editorialService;
        this.authorService = authorService;
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

    public void updateBook(int id, BookDto bookDto){
        Optional<BookEntity> bookOptional = bookRepository.findById(id);

        if(bookOptional.isPresent()){
            BookEntity book = bookOptional.get();

            if(bookDto.getTitle()!=null){
                book.setTitle(bookDto.getTitle());
            }
            if(bookDto.getDate()!=null){
                book.setDate(bookDto.getDate());
            }if(bookDto.getIsbn()!=null){
                book.setIsbn(bookDto.getIsbn());
            }if(bookDto.getCover()!=null){
                book.setCover(bookDto.getCover());
            }if(bookDto.getLanguage()!=null){
                book.setLanguage(bookDto.getLanguage());
            }if(bookDto.getSynopsis()!=null){
                book.setSynopsis(bookDto.getSynopsis());
            }if(bookDto.getPrice()!=null){
                book.setPrice(bookDto.getPrice());
            }if(bookDto.getPages()!=null){
                book.setPages(bookDto.getPages());
            }if(bookDto.getGenre()!=null){
                Optional<GenreEntity> newGenre = Optional.ofNullable(genreService.getById(bookDto.getGenre()));
                if (newGenre.isPresent()){
                    book.setGenre(newGenre.get());
                }else{
                    throw new IllegalArgumentException("Genre id doesnt exist");
                }

            }if(bookDto.getEditorial()!=null){
                Optional<EditorialEntity> newEditorial = Optional.ofNullable(editorialService.getById((bookDto.getEditorial())));
                if (newEditorial.isPresent()){
                    book.setEditorial(newEditorial.get());
                }else{
                    throw new IllegalArgumentException("Editorial id doesnt exist");
                }
            }if(bookDto.getAuthors()!=null){
                List<Integer> authorsIds = bookDto.getAuthors().stream()
                        .map(AuthorDto::getId).toList();

                List<AuthorEntity> authors = authorService.getByIds(authorsIds);

                book.setAuthors(authors);
            }

            bookRepository.save(book);
        }
    }

    public boolean exist(int id){
        return bookRepository.existsById(id);
    }


}

package com.jaf.bookstore.controller;

import com.jaf.bookstore.persistence.entity.BookEntity;
import com.jaf.bookstore.service.BookService;
import com.jaf.bookstore.service.DTO.BookDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Getter
@Setter
@RefreshScope
public class BookController {
    private final BookService bookService;
    @Value("${app.testProp}")
    private String testProp;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAll(){
        return ResponseEntity.ok(this.bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getById(@PathVariable int id){
        Optional<BookEntity> bookOptional = Optional.ofNullable(this.bookService.getById(id));

        return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookEntity>> getByTitle(@PathVariable String title){
        List<BookEntity> books = this.bookService.getByTitle(title);
        return responseListBooks(books);
    }
    @GetMapping("/language/{language}")
    public ResponseEntity<List<BookEntity>> getByLanguage(@PathVariable String language){
        List<BookEntity> books = this.bookService.getByLanguage(language);
        return responseListBooks(books);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookEntity> getByIsbn(@PathVariable String isbn){
        Optional<BookEntity> book = Optional.ofNullable(this.bookService.getByIsbn(isbn));

        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/range")
    public ResponseEntity<List<BookEntity>> getByPriceRange(@RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "9999") double max){
        List<BookEntity> books = this.bookService.getByPriceRange(min, max);

        return responseListBooks(books);
    }

   @GetMapping("/pages")
   public ResponseEntity<List<BookEntity>> getByPagesMax(@RequestParam(defaultValue = "9999") int pages){
        List<BookEntity> books = this.bookService.getByPagesMax(pages);

        return responseListBooks(books);
   }

   @GetMapping("/genre/{genre}")
   public ResponseEntity<List<BookEntity>> getByGenre(@PathVariable String genre){
        List<BookEntity> books = this.bookService.getByGenre(genre);

        return responseListBooks(books);
   }

    @GetMapping("/editorial/{editorial}")
    public ResponseEntity<List<BookEntity>> getByEditorial(@PathVariable String editorial){
        List<BookEntity> books = this.bookService.getByEditorial(editorial);

        return responseListBooks(books);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookEntity>> getByAuthor(@PathVariable String author){
        List<BookEntity> books = this.bookService.getByAuthor(author);

        return responseListBooks(books);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity updateBook(@PathVariable int bookId, @RequestBody BookDto bookUpdated){
        try{
            this.bookService.updateBook(bookId,bookUpdated);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<BookEntity> save (@RequestBody BookEntity book){
        BookEntity newBook = this.bookService.save(book);

        if(newBook != null){
            return ResponseEntity.ok(book);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        try{
            this.bookService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/test-prop")
    public String getTestProp(){
        return this.testProp;
    }

    private ResponseEntity<List<BookEntity>> responseListBooks(List<BookEntity> books){
        if(books.size()>0){
            return ResponseEntity.ok(books);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}

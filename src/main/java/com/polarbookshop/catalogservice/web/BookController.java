package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
class BookController {
    private final BookService bookService;

    @GetMapping
    Iterable<Book> get () {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Book post (@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    Book put(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}

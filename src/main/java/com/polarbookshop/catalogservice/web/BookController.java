package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class BookController {
    private final BookService bookService;

    @GetMapping
    Iterable<Book> get () {
        log.info("Retrieving all books");

        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    Book getByIsbn(@PathVariable String isbn) {
        log.info("Retrieving book with ISBN: {}", isbn);

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
        log.info("Updating book with ISBN: {}", isbn);

        return bookService.editBookDetails(isbn, book);
    }
}

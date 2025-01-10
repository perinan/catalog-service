package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.BDDMockito.given;

/**
 * Testing without a servlet server
 */
@WebMvcTest(BookController.class)
class BookControllerMvcTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404 () throws Exception {
        // Arrange
        String isbn = "73737313940";
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

        // Act
        var response = webTestClient
                .get()
                .uri("/books/{isbn}", isbn)
                .exchange();

        // Assert
        response.expectStatus().isNotFound();
    }
}
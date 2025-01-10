package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
//@AutoConfigureTestDatabase(
//        replace = AutoConfigureTestDatabase.Replace.NONE
//)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenExisting() {
        // Arrange
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "Title", "Author", 12.90, "Publisher");
        jdbcAggregateTemplate.insert(book);

        // Act
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        // Assert
        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }

    @Test
    void findSeveralBooks() {
        // Arrange
        var book1Isbn = "1234561237";
        var book1 = Book.of(book1Isbn, "Title1", "Author1", 1.0, "Publisher");
        var book2Isbn = "1234561238";
        var book2 = Book.of(book2Isbn, "Title2", "Author2", 2.0, "Publisher");
        jdbcAggregateTemplate.insertAll(List.of(book1, book2));

        // Act
        var allBooks = bookRepository.findAll().iterator();

        // Assert
        assertThat(allBooks.next().isbn()).isEqualTo(book1Isbn);
        assertThat(allBooks.next().isbn()).isEqualTo(book2Isbn);
        assertThat(allBooks.hasNext()).isFalse();
    }
}

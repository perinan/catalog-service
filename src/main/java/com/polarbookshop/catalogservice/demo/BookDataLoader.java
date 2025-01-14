package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
@RequiredArgsConstructor
class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    void loadBookTestData () {
        bookRepository.deleteAll();

        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Publisher");
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Publisher");

        bookRepository.saveAll(List.of(book1, book2));
    }

}

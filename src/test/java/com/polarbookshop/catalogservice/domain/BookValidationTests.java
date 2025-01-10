package com.polarbookshop.catalogservice.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds () {
        // Arrange
        var book = Book.of("1234567890", "Title", "Author", 9.90, "Publisher");

        // Act
        var violations = validator.validate(book);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails () {
        // Arrange
        var book = Book.of("a234567890", "Title", "Author", 9.90, "Publisher");

        // Act
        var violations = validator.validate(book);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}
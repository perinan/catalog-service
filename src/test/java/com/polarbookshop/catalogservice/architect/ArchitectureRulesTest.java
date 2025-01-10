package com.polarbookshop.catalogservice.architect;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@Disabled
class ArchitectureRulesTest {

    @Test
    void configurationClassesShouldBeNotPublic () {
        // Arrange
        ArchRule configurationClassesShouldNotBePublic =
                classes()
                        .that()
                        .areAnnotatedWith("org.springframework.context.annotation.Configuration")
                        .should().notBePublic();
        ArchRule configurationMethodsShouldNotBePublic =
                methods()
                        .that().areDeclaredInClassesThat().areAnnotatedWith("org.springframework.context.annotation.Configuration")
                        .should().notBePublic();
        // Assert
        configurationClassesShouldNotBePublic.check(new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.polarbookshop.catalogservice"));
        configurationMethodsShouldNotBePublic.check(new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.polarbookshop.catalogservice"));
    }

    @Test
    void controllerClassesShouldBeNotPublic () {
        // Arrange
        ArchRule controllerClassesShouldNotBePublic =
                classes()
                        .that()
                        .areAnnotatedWith(RestController.class)
                        .should().notBePublic();
        ArchRule controllerMethodsShouldNotBePublic =
                methods()
                        .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                        .should().notBePublic();
        // Assert
        controllerClassesShouldNotBePublic.check(new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.polarbookshop.catalogservice"));
        controllerMethodsShouldNotBePublic.check(new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.polarbookshop.catalogservice"));
    }
}

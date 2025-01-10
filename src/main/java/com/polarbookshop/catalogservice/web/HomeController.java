package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.config.PolarProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class HomeController {
    private final PolarProperties polarProperties;

    @GetMapping("/")
    String getGreeting() {
        return polarProperties.getGreeting();
    }
}

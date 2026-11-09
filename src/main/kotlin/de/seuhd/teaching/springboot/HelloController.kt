package de.seuhd.teaching.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/** Answers `GET /hello` with a fixed text. */
@RestController
class HelloController {
    @GetMapping("/hello")
    fun sayHello() = "Hello, Spring Boot!"
}

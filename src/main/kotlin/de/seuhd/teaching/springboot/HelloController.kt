package de.seuhd.teaching.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/** Handles `GET /hello` and returns a fixed text. */
@RestController
class HelloController {
    @GetMapping("/hello")
    fun sayHello() = "Hello, Spring Boot!"
}

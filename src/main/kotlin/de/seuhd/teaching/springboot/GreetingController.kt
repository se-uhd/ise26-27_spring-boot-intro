package de.seuhd.teaching.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Answers `GET /greeting` with a greeting for the query parameter `name`, as in `GET /greeting?name=Kitty`. Spring
 * passes a bean that implements [GreetingService] to the constructor, so the controller depends only on the
 * interface and not on [GreetingServiceImpl].
 */
@RestController
class GreetingController(private val greetingService: GreetingService) {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(defaultValue = "World") name: String) = greetingService.greet(name)
}

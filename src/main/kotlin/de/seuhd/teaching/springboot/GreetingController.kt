package de.seuhd.teaching.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Handles `GET /greeting` and returns a greeting for the query parameter `name`, as in `GET /greeting?name=Kitty`. The
 * default name is "World". The constructor parameter has the interface type [GreetingService], and Spring passes the
 * bean that implements this interface. The controller does not depend on the class [GreetingServiceImpl].
 */
@RestController
class GreetingController(private val greetingService: GreetingService) {
    @GetMapping("/greeting")
    fun greeting(@RequestParam(defaultValue = "World") name: String) = greetingService.greet(name)
}

package de.seuhd.teaching.springboot

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests [GreetingServiceImpl] without Spring. With constructor injection, the test can create the [GreetingProperties]
 * object and pass it to the constructor.
 */
class GreetingServiceImplTest {
    @Test
    fun `greet puts the salutation before the name`() {
        val service = GreetingServiceImpl(GreetingProperties(salutation = "Hi"))

        assertEquals("Hi, Kitty!", service.greet("Kitty"))
    }
}

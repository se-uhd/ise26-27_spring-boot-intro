package de.seuhd.teaching.springboot

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests [GreetingServiceImpl] without Spring. Because the class receives its dependency through the constructor, the
 * test can pass its own [GreetingProperties].
 */
class GreetingServiceImplTest {
    @Test
    fun `greet puts the salutation before the name`() {
        val service = GreetingServiceImpl(GreetingProperties(salutation = "Hi"))

        assertEquals("Hi, Kitty!", service.greet("Kitty"))
    }
}

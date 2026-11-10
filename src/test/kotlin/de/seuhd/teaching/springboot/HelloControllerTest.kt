package de.seuhd.teaching.springboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

/**
 * Starts the application without an HTTP server. MockMvc passes each request to the controllers inside the test
 * process.
 */
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest(@Autowired private val mockMvc: MockMvc) {
    @Test
    fun `GET hello returns the fixed text`() {
        mockMvc.get("/hello").andExpect {
            status { isOk() }
            content { string("Hello, Spring Boot!") }
        }
    }
}

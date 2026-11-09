package de.seuhd.teaching.springboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

/** Tests `GET /greeting` with the salutation from the default configuration in `application.yaml`. */
@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest(@Autowired private val mockMvc: MockMvc) {
    @Test
    fun `GET greeting without a name greets the world`() {
        mockMvc.get("/greeting").andExpect {
            status { isOk() }
            content { string("Hello, World!") }
        }
    }

    @Test
    fun `GET greeting with a name greets that name`() {
        mockMvc.get("/greeting") { param("name", "Kitty") }.andExpect {
            status { isOk() }
            content { string("Hello, Kitty!") }
        }
    }
}

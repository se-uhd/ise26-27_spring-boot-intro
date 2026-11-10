package de.seuhd.teaching.springboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

/** Tests `GET /greeting` with the dev profile, for which `application.yaml` sets the salutation to "Hi". */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class GreetingControllerDevProfileTest(@Autowired private val mockMvc: MockMvc) {
    @Test
    fun `GET greeting uses the salutation of the dev profile`() {
        mockMvc.get("/greeting").andExpect {
            status { isOk() }
            content { string("Hi, World!") }
        }
    }
}

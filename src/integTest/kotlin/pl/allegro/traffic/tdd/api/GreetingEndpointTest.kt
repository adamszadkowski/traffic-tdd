package pl.allegro.traffic.tdd.api

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import pl.allegro.traffic.tdd.infrastructure.InMemoryGreetingRepository

@SpringBootTest
@AutoConfigureMockMvc
class GreetingEndpointTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val greetingRepository: InMemoryGreetingRepository,
) {

    @BeforeEach
    fun setUp() {
        greetingRepository.clear()
    }

    @Test
    fun `get default greeting`() {
        mockMvc.get("/greeting").andExpect {
            status { is2xxSuccessful() }
            content { json(""" { "message": "hello world" } """) }
        }
    }

    @Test
    fun `update greeting`() {
        mockMvc.put("/greeting") {
            contentType = MediaType.APPLICATION_JSON
            content = """ { "message": "updated greeting" } """
        }.andExpect {
            status { is2xxSuccessful() }
            content { json(""" { "message": "updated greeting" } """) }
        }

        mockMvc.get("/greeting").andExpect {
            status { is2xxSuccessful() }
            content { json(""" { "message": "updated greeting" } """) }
        }
    }

    @Test
    fun `fail to update incorrect version`() {
        mockMvc.put("/greeting") {
            contentType = MediaType.APPLICATION_JSON
            content = """ { "message": "updated greeting", "lastVersion": "12" } """
        }.andExpect {
            status { is4xxClientError() }
        }
    }
}

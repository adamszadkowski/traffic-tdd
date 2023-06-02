package pl.allegro.traffic.tdd.api

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import pl.allegro.traffic.tdd.ApplicationTest

@ApplicationTest
@AutoConfigureMockMvc
@TestPropertySource(properties = ["greeting.default-message=Hello world"])
class GreetingEndpointTest(
    @Autowired private val mockMvc: MockMvc,
) {

    @Test
    fun `access denied`() {
        mockMvc.get("/greeting").andExpect {
            status { isUnauthorized() }
        }
    }

    @Nested
    inner class `logged in user` {
        private val loggedInUser = "loggedInUser"

        @Test
        fun `get default greeting`() {
            mockMvc.get("/greeting") {
                with(user(loggedInUser))
            }.andExpect {
                status { is2xxSuccessful() }
                content { json(""" { "message": "Hello world", "version": 0 } """) }
            }
        }

        @Test
        fun `update greeting`() {
            mockMvc.put("/greeting") {
                contentType = MediaType.APPLICATION_JSON
                content = """ { "message": "updated greeting", "lastVersion": 0 } """
                with(user(loggedInUser))
            }.andExpect {
                status { is2xxSuccessful() }
                content { json(""" { "message": "updated greeting", "version": 1 } """) }
            }

            mockMvc.get("/greeting") {
                with(user(loggedInUser))
            }.andExpect {
                status { is2xxSuccessful() }
                content { json(""" { "message": "updated greeting", "version": 1 } """) }
            }
        }

        @Test
        fun `fail to update incorrect version`() {
            mockMvc.put("/greeting") {
                contentType = MediaType.APPLICATION_JSON
                content = """ { "message": "updated greeting", "lastVersion": "12" } """
                with(user(loggedInUser))
            }.andExpect {
                status { isConflict() }
            }
        }
    }

    @Nested
    inner class `two users` {

        @BeforeEach
        fun `two users have updated greeting`() {
            updateGreeting(user = "user1", message = "user1 greeting")
            updateGreeting(user = "user2", message = "user2 greeting")
        }

        @ParameterizedTest
        @CsvSource(
            "user1, user1 greeting",
            "user2, user2 greeting",
        )
        fun `two users can update`(userId: String, greeting: String) {
            mockMvc.get("/greeting") {
                with(user(userId))
            }.andExpect {
                status { is2xxSuccessful() }
                content { json(""" { "message": "$greeting", "version": 1 } """) }
            }
        }

        private fun updateGreeting(user: String, message: String) {
            mockMvc.put("/greeting") {
                contentType = MediaType.APPLICATION_JSON
                content = """ { "message": "$message", "lastVersion": 0 } """
                with(user(user))
            }.andExpect {
                status { is2xxSuccessful() }
            }
        }
    }
}

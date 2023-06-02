package pl.allegro.traffic.tdd.security

import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import pl.allegro.traffic.tdd.ApplicationTest
import strikt.api.expectThat
import strikt.assertions.isTrue
import java.net.URI
import java.util.*

@ApplicationTest
class UserSecurityTest(
    @LocalServerPort private val port: Int,
) {

    @Test
    fun `log in with http basic`() {
        val request = RequestEntity<Any>(
            HttpHeaders().apply {
                this["Authorization"] = "Basic ${Base64.getEncoder().encodeToString("user:password".toByteArray())}"
            },
            HttpMethod.GET,
            URI.create("http://localhost:$port/greeting")
        )

        val statusCode = RestTemplate().exchange<String>(request).statusCode

        expectThat(statusCode.is2xxSuccessful).isTrue()
    }
}
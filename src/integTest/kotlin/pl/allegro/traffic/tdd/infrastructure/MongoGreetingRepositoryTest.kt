package pl.allegro.traffic.tdd.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource
import pl.allegro.traffic.tdd.ApplicationTest
import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

@ApplicationTest
@TestPropertySource(properties = ["greeting.default-message=default greeting value"])
class MongoGreetingRepositoryTest(
    @Autowired private val greetingRepository: MongoGreetingRepository,
) {

    @Test
    fun `get default greeting`() {
        expectThat(greetingRepository.get()).isEqualTo(Greeting(message = "default greeting value", version = 0))
    }

    @Test
    fun `update greeting`() {
        val updated = greetingRepository.update(message = "updated greeting", lastVersion = 0)

        expectThat(updated)
            .isEqualTo(Greeting(message = "updated greeting", version = 1))
            .isEqualTo(greetingRepository.get())
    }

    @Test
    fun `fail to update incorrect version`() {
        expectThrows<GreetingRepository.VersionMismatchException> {
            greetingRepository.update(message = "update greeting", lastVersion = 5)
        }
    }
}

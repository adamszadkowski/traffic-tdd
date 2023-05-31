package pl.allegro.traffic.tdd.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import pl.allegro.traffic.tdd.ApplicationTest
import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ApplicationTest
class MongoGreetingRepositoryTest(
    @Autowired private val greetingRepository: MongoGreetingRepository,
) {

    @TestConfiguration
    class TestBeans {

        @Bean
        fun greetingRepository(crudRepository: GreetingCrudRepository): GreetingRepository =
            MongoGreetingRepository(crudRepository)
    }

    @Test
    fun `get default greeting`() {
        expectThat(greetingRepository.get()).isEqualTo(Greeting(message = "Hello world", version = 0))
    }

    @Test
    fun `update greeting`() {
        val updated = greetingRepository.update(message = "updated greeting", lastVersion = 0)

        expectThat(updated)
            .isEqualTo(Greeting(message = "updated greeting", version = 1))
            .isEqualTo(greetingRepository.get())
    }
}

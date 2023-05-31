package pl.allegro.traffic.tdd.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@SpringBootTest
@ActiveProfiles("integration")
class MongoGreetingRepositoryTest(
    @Autowired private val greetingRepository: MongoGreetingRepository,
) {

    @TestConfiguration
    class TestBeans {

        @Bean
        fun greetingRepository(): GreetingRepository =
            MongoGreetingRepository()
    }

    @Test
    fun `get default greeting`() {
        expectThat(greetingRepository.get()).isEqualTo(Greeting(message = "Hello world", version = 0))
    }
}

package pl.allegro.traffic.tdd.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.traffic.tdd.domain.GreetingRepository
import pl.allegro.traffic.tdd.domain.GreetingService
import pl.allegro.traffic.tdd.infrastructure.GreetingCrudRepository
import pl.allegro.traffic.tdd.infrastructure.MongoGreetingRepository

@Configuration
class GreetingConfiguration {

    @Bean
    fun greetingService(greetingRepository: GreetingRepository): GreetingService =
        GreetingService(greetingRepository)

    @Bean
    fun greetingRepository(crudRepository: GreetingCrudRepository): GreetingRepository =
        MongoGreetingRepository(crudRepository)
}

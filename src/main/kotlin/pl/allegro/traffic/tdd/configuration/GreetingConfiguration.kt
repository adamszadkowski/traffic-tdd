package pl.allegro.traffic.tdd.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.traffic.tdd.domain.GreetingRepository
import pl.allegro.traffic.tdd.domain.GreetingService
import pl.allegro.traffic.tdd.infrastructure.GreetingCrudRepository
import pl.allegro.traffic.tdd.infrastructure.MongoGreetingRepository

@Configuration
@EnableConfigurationProperties(GreetingProperties::class)
class GreetingConfiguration {

    @Bean
    fun greetingService(greetingRepository: GreetingRepository): GreetingService =
        GreetingService(greetingRepository)

    @Bean
    fun greetingRepository(
        crudRepository: GreetingCrudRepository,
        properties: GreetingProperties,
    ): GreetingRepository =
        MongoGreetingRepository(crudRepository, defaultMessage = properties.defaultMessage)
}

@ConfigurationProperties(prefix = "greeting")
data class GreetingProperties(
    val defaultMessage: String,
)

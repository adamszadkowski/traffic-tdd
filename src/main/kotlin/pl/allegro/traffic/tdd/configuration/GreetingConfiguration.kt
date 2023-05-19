package pl.allegro.traffic.tdd.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.traffic.tdd.domain.GreetingService

@Configuration
class GreetingConfiguration {

    @Bean
    fun greetingService(): GreetingService =
        GreetingService()
}

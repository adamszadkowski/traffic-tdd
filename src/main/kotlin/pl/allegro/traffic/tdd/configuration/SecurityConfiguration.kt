package pl.allegro.traffic.tdd.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf().disable()
            .authorizeHttpRequests {
                it.requestMatchers("/greeting").authenticated()
            }
            .httpBasic().and()
            .build()
}
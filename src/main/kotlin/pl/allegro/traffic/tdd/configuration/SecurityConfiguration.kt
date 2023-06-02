package pl.allegro.traffic.tdd.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun userDetailsService(): UserDetailsService =
        UserDetailsService { username ->
            User.withUsername(username)
                .password("{noop}password")
                .roles("USER")
                .build()
        }

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
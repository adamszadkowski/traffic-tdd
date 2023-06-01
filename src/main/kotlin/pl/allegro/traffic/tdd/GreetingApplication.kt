package pl.allegro.traffic.tdd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GreetingApplication

fun main(args: Array<String>) {
    createSpringApplication().run(*args)
}

fun createSpringApplication() = SpringApplication(GreetingApplication::class.java)

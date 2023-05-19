package pl.allegro.traffic.tdd.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint {

    @GetMapping
    fun getGreeting() = "hello world"
}
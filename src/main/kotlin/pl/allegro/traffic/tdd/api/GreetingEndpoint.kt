package pl.allegro.traffic.tdd.api

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicReference

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint {
    private val message = AtomicReference("hello world")

    @GetMapping
    fun getGreeting() =
        GreetingResponse(message.get())

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest) =
        UpdateResponse(message.updateAndGet { request.message })
}

data class GreetingResponse(
    val message: String,
)

data class UpdateRequest(
    val message: String,
)

data class UpdateResponse(
    val message: String,
)
package pl.allegro.traffic.tdd.api

import org.springframework.web.bind.annotation.*
import pl.allegro.traffic.tdd.domain.GreetingService

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint(
    private val greetingService: GreetingService,
) {

    @GetMapping
    fun getGreeting() =
        GreetingResponse(greetingService.get())

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest) =
        UpdateResponse(greetingService.update(request.message))
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
package pl.allegro.traffic.tdd.api

import org.springframework.web.bind.annotation.*
import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingService

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint(
    private val greetingService: GreetingService,
) {

    @GetMapping
    fun getGreeting() =
        greetingService.get().toGreetingDto()

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest) =
        greetingService.update(request.message).toUpdateDto()

    private fun Greeting.toGreetingDto() = GreetingResponse(message)

    private fun Greeting.toUpdateDto() = UpdateResponse(message)
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
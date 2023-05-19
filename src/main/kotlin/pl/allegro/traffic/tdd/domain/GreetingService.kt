package pl.allegro.traffic.tdd.domain

class GreetingService(
    private val greetingRepository: GreetingRepository,
) {
    fun get(): String = greetingRepository.get()
    fun update(message: String): String = greetingRepository.update(message)
}

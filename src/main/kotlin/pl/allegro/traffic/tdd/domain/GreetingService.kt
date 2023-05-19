package pl.allegro.traffic.tdd.domain

class GreetingService(
    private val greetingRepository: GreetingRepository,
) {
    fun get(): Greeting = greetingRepository.get()
    fun update(message: String, lastVersion: Int): Greeting = greetingRepository.update(message, lastVersion)
}

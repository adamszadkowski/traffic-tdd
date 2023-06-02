package pl.allegro.traffic.tdd.domain

class GreetingService(
    private val greetingRepository: GreetingRepository,
) {
    fun get(userId: String): Greeting =
        greetingRepository.get(userId)

    fun update(userId: String, message: String, lastVersion: Int): Greeting =
        greetingRepository.update(userId, message, lastVersion)
}

package pl.allegro.traffic.tdd.domain

interface GreetingRepository {
    fun get(): Greeting
    fun update(message: String, lastVersion: Int): Greeting

    class VersionMismatchException : RuntimeException()
}

data class Greeting(
    val message: String,
)

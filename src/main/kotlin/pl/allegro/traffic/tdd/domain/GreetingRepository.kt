package pl.allegro.traffic.tdd.domain

interface GreetingRepository {
    fun get(): Greeting
    fun update(message: String): Greeting
}

data class Greeting(
    val message: String,
)

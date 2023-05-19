package pl.allegro.traffic.tdd.domain

interface GreetingRepository {
    fun get(): String
    fun update(message: String): String
}

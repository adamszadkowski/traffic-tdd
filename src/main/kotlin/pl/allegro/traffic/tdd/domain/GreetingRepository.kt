package pl.allegro.traffic.tdd.domain

interface GreetingRepository {
    fun get(userId: String): Greeting
    fun update(userId: String, message: String, lastVersion: Int): Greeting

    class VersionMismatchException : RuntimeException()
}

data class Greeting(
    val message: String,
    val version: Int,
)

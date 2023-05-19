package pl.allegro.traffic.tdd.infrastructure

import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import java.util.concurrent.atomic.AtomicReference

class InMemoryGreetingRepository : GreetingRepository {
    private val message = AtomicReference(DEFAULT_GREETING)

    override fun get(): Greeting = message.get().toDomain()
    override fun update(message: String, lastVersion: Int): Greeting = this.message.updateAndGet {
        if (lastVersion == it.version) {
            it.copy(message = message, version = it.version + 1)
        } else {
            throw GreetingRepository.VersionMismatchException()
        }
    }.toDomain()

    fun clear() {
        message.set(DEFAULT_GREETING)
    }

    private fun InMemoryGreetingEntity.toDomain() =
        Greeting(message, version)

    companion object {
        private val DEFAULT_GREETING = InMemoryGreetingEntity(message = "hello world", version = 0)
    }
}

data class InMemoryGreetingEntity(
    val message: String,
    val version: Int,
)

package pl.allegro.traffic.tdd.infrastructure

import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import java.util.concurrent.atomic.AtomicReference

class InMemoryGreetingRepository : GreetingRepository {
    private val message = AtomicReference(Greeting("hello world"))

    override fun get(): Greeting = message.get()
    override fun update(message: String): Greeting = this.message.updateAndGet {
        it.copy(message = message)
    }
}

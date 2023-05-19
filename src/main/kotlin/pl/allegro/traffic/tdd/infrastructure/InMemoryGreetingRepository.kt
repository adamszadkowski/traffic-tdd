package pl.allegro.traffic.tdd.infrastructure

import pl.allegro.traffic.tdd.domain.GreetingRepository
import java.util.concurrent.atomic.AtomicReference

class InMemoryGreetingRepository : GreetingRepository {
    private val message = AtomicReference("hello world")

    override fun get(): String = message.get()
    override fun update(message: String): String = this.message.updateAndGet { message }
}

package pl.allegro.traffic.tdd.domain

import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun get(): String = message.get()
    fun update(message: String): String = this.message.updateAndGet { message }
}

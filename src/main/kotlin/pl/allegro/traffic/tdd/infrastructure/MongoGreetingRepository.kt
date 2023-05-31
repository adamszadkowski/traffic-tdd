package pl.allegro.traffic.tdd.infrastructure

import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository

class MongoGreetingRepository : GreetingRepository {
    override fun get(): Greeting {
        return Greeting(message = "Hello world", version = 0)
    }

    override fun update(message: String, lastVersion: Int): Greeting {
        TODO("Not yet implemented")
    }

}

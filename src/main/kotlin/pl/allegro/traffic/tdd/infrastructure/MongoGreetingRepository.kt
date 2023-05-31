package pl.allegro.traffic.tdd.infrastructure

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.repository.CrudRepository
import pl.allegro.traffic.tdd.domain.Greeting
import pl.allegro.traffic.tdd.domain.GreetingRepository
import kotlin.jvm.optionals.getOrNull

class MongoGreetingRepository(
    private val crudRepository: GreetingCrudRepository,
) : GreetingRepository {
    override fun get(): Greeting =
        crudRepository.findById("single")
            .getOrNull()?.toDomain()
            ?: Greeting(message = "Hello world", version = 0)

    override fun update(message: String, lastVersion: Int): Greeting =
        MongoGreetingEntity(id = "single", message = message, version = lastVersion)
            .let(crudRepository::save)
            .toDomain()

    private fun MongoGreetingEntity.toDomain() =
        Greeting(
            message = message,
            version = version,
        )
}

interface GreetingCrudRepository : CrudRepository<MongoGreetingEntity, String>

data class MongoGreetingEntity(
    @Id val id: String,
    val message: String,
    @Version val version: Int,
)

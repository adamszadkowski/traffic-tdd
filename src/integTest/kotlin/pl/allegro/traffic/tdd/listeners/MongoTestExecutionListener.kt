package pl.allegro.traffic.tdd.listeners

import org.bson.Document
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener

class MongoTestExecutionListener : TestExecutionListener {
    override fun afterTestMethod(testContext: TestContext) {
        val mongoOperations = testContext.applicationContext.getBean(MongoOperations::class.java)

        mongoOperations.collectionNames.forEach { collection ->
            mongoOperations.getCollection(collection).deleteMany(Document())
        }
    }
}

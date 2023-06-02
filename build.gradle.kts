plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"

    id("org.springframework.boot") version "3.0.6"
    id("nebula.integtest") version "9.6.3"
}

repositories {
    mavenCentral()
}

springBoot {
    mainClass.set("pl.allegro.traffic.tdd.DummyMain")
}

dependencies {
    implementation(enforcedPlatform(kotlin("bom")))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.6"))
    implementation(platform("org.junit:junit-bom:5.9.3"))

    constraints {
        implementation("io.strikt:strikt-core:0.34.1")
        implementation("io.mockk:mockk:1.13.5")
        implementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring30x:4.6.2")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.strikt:strikt-core")
    testImplementation("io.mockk:mockk")

    integTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integTestImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring30x")
    integTestImplementation("org.springframework.security:spring-security-test")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}

package pl.allegro.traffic.tdd

fun main(args: Array<String>) {
    createSpringApplication()
        .apply {
            setAdditionalProfiles("local")
        }
        .run(*args)
}

package pl.allegro.traffic.tdd

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty

class PrimeFactorsTest {

    @Test
    fun `get prime factor`() {
        expectThat(primeFactorOf(1)).isEmpty()
        expectThat(primeFactorOf(2)).containsExactly(2)
        expectThat(primeFactorOf(3)).containsExactly(3)
        expectThat(primeFactorOf(4)).containsExactly(2, 2)
        expectThat(primeFactorOf(5)).containsExactly(5)
        expectThat(primeFactorOf(6)).containsExactly(2, 3)
        expectThat(primeFactorOf(7)).containsExactly(7)
        expectThat(primeFactorOf(8)).containsExactly(2, 2, 2)
        expectThat(primeFactorOf(9)).containsExactly(3, 3)
        expectThat(primeFactorOf(2 * 2 * 3 * 3 * 7 * 13)).containsExactly(2, 2, 3, 3, 7, 13)
    }

    private fun primeFactorOf(number: Int): List<Int> {
        val factors = mutableListOf<Int>()
        var n = number
        var k = 2

        while (n > 1) {
            while (n % k == 0) {
                factors += k
                n /= k
            }
            k++
        }

        return factors
    }
}

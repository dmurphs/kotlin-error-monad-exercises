package factorialExample

import Failure
import Success
import org.junit.jupiter.api.Assertions.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FactorialSpec: Spek({
    describe("safeFactorial") {
        it("returns error for negative input") {
            assertEquals(Failure(FACTORIAL_NEGATIVE_INPUT_MESSAGE), safeFactorial(-1))
        }
        it("returns 1 if 0 is input") {
            assertEquals(Success(1), safeFactorial(0))
        }
        it("returns 120 if 5 is input") {
            assertEquals(Success(120), safeFactorial(5))
        }
    }

    describe("unsafeFactorial") {
        it("returns error for negative input") {
            val err = assertThrows(Exception::class.java) {
                unsafeFactorial(-1)
            }

            assertEquals(FACTORIAL_NEGATIVE_INPUT_MESSAGE, err.message)
        }
        it("returns 1 if 0 is input") {
            assertEquals(1, unsafeFactorial(0))
        }
        it("returns 120 if 5 is input") {
            assertEquals(120, unsafeFactorial(5))
        }
    }
})


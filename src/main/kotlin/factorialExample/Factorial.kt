package factorialExample

import Either
import Success
import Failure
import map

const val FACTORIAL_NEGATIVE_INPUT_MESSAGE = "Factorial not defined for negative input"

// We can use type system to encode the possibility of failure
// Let's use a union type which can be represent either failure or success
fun safeFactorial(n: Int): Either<String, Int> =
    when {
        n < 0  -> {
            Failure(FACTORIAL_NEGATIVE_INPUT_MESSAGE)
        }
        n == 0 -> {
            Success(1)
        }
        else   -> {
            safeFactorial(n - 1).map { it * n }
//          alternate (probably better) implementation: Success((1..n).reduce { acc, i -> i * acc })
        }
    }

// factorial function which throws if invalid argument
fun unsafeFactorial(n: Int): Int =
    when {
        n < 0 -> {
            throw Exception(FACTORIAL_NEGATIVE_INPUT_MESSAGE)
        }
        n == 0 -> {
            1
        }
        else -> {
            n * unsafeFactorial(n - 1)
        }
    }


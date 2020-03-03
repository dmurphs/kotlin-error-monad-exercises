package factorialExample

import flatMap
import Either
import map

// An example for a case which uses the full power of the result monad

fun unsafeSumFactorial(x: Int, y: Int): Int {
    val xFactorial = unsafeFactorial(x)
    val yFactorial = unsafeFactorial(y)

    return xFactorial + yFactorial
}

fun safeSumFactorial(x: Int, y: Int): Either<String, Int> {
    val xFactorialResult: Either<String, Int> = safeFactorial(x)

    // Short circuits here if xFactorialResult is error due to how we define flatMap
    return xFactorialResult.flatMap { xFactorial: Int ->
        val yFactorialResult: Either<String, Int> = safeFactorial(y)
        yFactorialResult.map { yFactorial: Int ->
            xFactorial + yFactorial
        }
    }
}

fun main() {
    println("safeSumFactorial Results:")
    println("\tsafeSumFactorial(2, 3) -> ${safeSumFactorial(2, 3)}")
    println("\tsafeSumFactorial(-1, 2) -> ${safeSumFactorial(-1, 2)}")
    println()
    println("unsafeSumFactorial Results: ")
    println("\tunsafeSumFactorial(2, 3) -> ${unsafeSumFactorial(2, 3)}")
    println("\tunsafeSumFactorial(-1, 2) -> ${unsafeSumFactorial(-1, 2)}")
    // This throws because n == -1
    unsafeFactorial(-1)
}
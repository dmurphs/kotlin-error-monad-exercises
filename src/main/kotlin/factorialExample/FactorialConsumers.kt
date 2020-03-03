package factorialExample

import Either
import Failure
import Success
import map

// emulate a response payload
sealed class Response
data class ErrorResponse(val message: String) : Response()
data class SuccessResponse(val body: Any) : Response()

fun safeDoubleFactorialResponse(x: Int): Response {
    val xFactorialResult: Either<String, Int> = safeFactorial(x)

    val doubledFactorialResult: Either<String, Int> =  xFactorialResult.map { it * 2 }

    return when (doubledFactorialResult) {
        is Failure -> ErrorResponse(doubledFactorialResult.value)
        is Success -> SuccessResponse(doubledFactorialResult.value)
    }
}

// As a consumer of unsafeFactorial, the compiler won't tell me that this may throw
// If one needed this code to be safe, they would need to look at the implementation of unsafeFactorial to know it throws, and then add a try/catch

// To account for possibility of failure, we need to know about the possibility of an exception and add a try/catch
// It would be easy to implement this without accounting for the possibility of failure since the compiler can't help us
fun unsafeDoubleFactorialResponse(x: Int): Response {
    try {
        val xFactorial = unsafeFactorial(x)

        return SuccessResponse(xFactorial * 2)
    } catch (err: Exception) {
        return ErrorResponse(err.message ?: "")
    }
}

fun main() {
    println("safeDoubleFactorialResponse Results:")
    println("\tsafeDoubleFactorialResponse(2) -> ${safeDoubleFactorialResponse(2)}")
    println("\tsafeDoubleFactorialResponse(-1) -> ${safeDoubleFactorialResponse(-1)}")
    println()
    println("unsafeDoubleFactorialResponse Results: ")
    println("\tunsafeDoubleFactorialResponse(2) -> ${unsafeDoubleFactorialResponse(2)}")
    println("\tunsafeDoubleFactorialResponse(-1) -> ${unsafeDoubleFactorialResponse(-1)}")
}
sealed class Either<out F, out S>
data class Failure<out T>(val value: T) : Either<T, Nothing>()
data class Success<out T>(val value: T) : Either<Nothing, T>()

inline fun <F, S, T> Either<F, S>.map(f: (S) -> T): Either<F, T> =
    when (this) {
        is Failure -> this
        is Success -> Success(f(this.value))
    }

inline fun <F, S, T> Either<F, S>.flatMap(f: (S) -> Either<F, T>): Either<F, T> =
    when (this) {
        is Failure   -> this
        is Success -> f(this.value)
    }

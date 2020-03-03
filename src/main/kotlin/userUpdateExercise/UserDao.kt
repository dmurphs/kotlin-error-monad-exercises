package userUpdateExercise

import Either
import Failure
import Success
import java.time.LocalDate

data class User(
    val username: String,
    val birthDate: LocalDate
)

const val USER_ALREADY_EXISTS_ERROR = "user already exists"
const val USER_DOES_NOT_EXIST_ERROR = "user does not exist"

// In memory database for users
class UserDao {
    private val users = mutableListOf<User>()

    fun insertUser(user: User): Either<String, Unit> = TODO()

    fun getUser(username: String): Either<String, User> = TODO()
}
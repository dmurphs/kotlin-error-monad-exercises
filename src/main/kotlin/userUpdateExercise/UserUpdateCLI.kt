package userUpdateExercise

import Either
import Failure
import Success
import java.time.LocalDate

const val DATE_PARSE_ERROR = "Could not parse date"

class UserLogic
constructor(val userDao: UserDao){

    fun addUser(username: String, encodedBirthDate: String): Either<String, Unit> = TODO()

    fun getUserAge(username: String, now: LocalDate = LocalDate.now()): Either<String, Long> = TODO()

    private fun safeReadDate(encodedDate: String): Either<String, LocalDate> = TODO()
}

fun main() {
    val userDao = UserDao()
    val userLogic = UserLogic(userDao)

    while (true) {
        print("Enter action: ")
        val action = readLine()

        when {
            action == "addUser" -> {
                print("Enter username: ")
                val username = readLine()
                print("Enter birthdate (yyyy-MM-DD): ")
                val encodedBirthDate = readLine()

                if (username != null && encodedBirthDate != null) {
                    when (val insertResult = userLogic.addUser(username, encodedBirthDate)) {
                        is Success -> println("Success!")
                        is Failure -> println(insertResult.value)
                    }
                } else {
                    println("Must enter username and birthdate")
                }
            }
            action == "getUser" -> {
                print("Enter username: ")
                val username = readLine()
                username?.let {
                    when (val userBirthday = userLogic.getUserAge(username)) {
                        is Success -> println(userBirthday.value)
                        is Failure -> println(userBirthday.value)
                    }
                } ?: println("Must enter username")
            }
        }
    }

}
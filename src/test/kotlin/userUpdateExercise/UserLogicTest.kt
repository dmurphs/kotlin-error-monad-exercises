package userUpdateExercise

import Failure
import Success
import org.junit.jupiter.api.Assertions.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate

class UserLogicTest : Spek({
    describe("UserLogic") {
        it("add user") {
            val userDao = UserDao()
            val userLogic = UserLogic(userDao)

            val username = "test-user-name"
            val encodedBirthDate = "1990-02-02"

            val addUserResult = userLogic.addUser(username, encodedBirthDate)
            assertEquals(Success(Unit), addUserResult)

            val insertedUser = userDao.getUser(username)
            assertEquals(Success(User(username, LocalDate.parse(encodedBirthDate))), insertedUser)
        }

        it("add user with malformed birthdate") {
            val userDao = UserDao()
            val userLogic = UserLogic(userDao)

            val username = "test-user-name"
            val encodedBirthDate = "abcxyz"

            val addUserResult = userLogic.addUser(username, encodedBirthDate)
            assertEquals(Failure(DATE_PARSE_ERROR), addUserResult)
        }

        it("add user with username already in use") {
            val userDao = UserDao()
            val userLogic = UserLogic(userDao)

            val username = "test-user-name"
            val encodedBirthDate = "1990-02-02"

            userDao.insertUser(User(username, LocalDate.now()))

            val addUserResult = userLogic.addUser(username, encodedBirthDate)
            assertEquals(Failure(USER_ALREADY_EXISTS_ERROR), addUserResult)
        }

        it("get user age") {
            val userDao = UserDao()
            val userLogic = UserLogic(userDao)

            val userAge = 10L
            val username = "test-user-name"
            val currentDate = LocalDate.now()
            val birthDate = currentDate.minusYears(userAge)
            val user = User(username, birthDate)
            userDao.insertUser(user)

            val birthdayResult = userLogic.getUserAge(username, currentDate)
            assertEquals(Success(userAge), birthdayResult)
        }

        it("get user age for nonexistent user") {
            val userDao = UserDao()
            val userLogic = UserLogic(userDao)

            val birthdayResult = userLogic.getUserAge("someone")
            assertEquals(Failure(USER_DOES_NOT_EXIST_ERROR), birthdayResult)
        }
    }
})
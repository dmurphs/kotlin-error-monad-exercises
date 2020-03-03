package userUpdateExercise

import Failure
import Success
import org.junit.jupiter.api.Assertions.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object UserDaoSpec : Spek({
    describe("UserDao") {
        it("insert and retrieve user") {
            val userDao = UserDao()
            val username = "testuser"
            val newUser = User(username, LocalDate.from(Instant.now().minus(10L, ChronoUnit.YEARS)))
            val insertUserResult = userDao.insertUser(newUser)
            assertEquals(Success(Unit), insertUserResult)

            assertEquals(Success(newUser), userDao.getUser(username))
        }

        it("get nonexistent user") {
            val userDao = UserDao()
            assertEquals(Failure(USER_DOES_NOT_EXIST_ERROR), userDao.getUser("testuser"))
        }

        it("insert user with username already in use") {
            val userDao = UserDao()
            val username = "testuser"
            val newUser = User(username, LocalDate.from(Instant.now().minus(10L, ChronoUnit.YEARS)))
            userDao.insertUser(newUser)

            val insertResult = userDao.insertUser(newUser)
            assertEquals(Failure(USER_ALREADY_EXISTS_ERROR), insertResult)
        }
    }
})
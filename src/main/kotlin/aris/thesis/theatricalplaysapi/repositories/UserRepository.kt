package aris.thesis.theatricalplaysapi.repositories

import aris.thesis.theatricalplaysapi.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

    fun findByEmail(email: String): User?

}
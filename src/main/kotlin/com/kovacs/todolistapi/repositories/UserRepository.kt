package com.kovacs.todolistapi.repositories

import com.kovacs.todolistapi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Int> {

    @Query("SELECT u FROM User u")
    fun getListUsers(): List<User>
}
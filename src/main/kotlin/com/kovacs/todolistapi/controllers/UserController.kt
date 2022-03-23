package com.kovacs.todolistapi.controllers

import com.kovacs.todolistapi.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class UserController (private val userRepository: UserRepository) {

    @GetMapping("/user/list")
    fun getListUsers():ResponseEntity<Any>{
        val users = userRepository.getListUsers()

        return ResponseEntity.ok(users)
    }

}
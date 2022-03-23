package com.kovacs.todolistapi.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kovacs.todolistapi.models.User
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

    @PostMapping("/user/create")
    fun postUser(
        @RequestBody json: String
    ): ResponseEntity<Any> {
        val user = userRepository.save(jacksonObjectMapper().readValue(json, User::class.java))

        return ResponseEntity.ok(json)
    }
}
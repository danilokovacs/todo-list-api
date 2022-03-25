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

    @PutMapping("/user/update/{id}")
    fun putUser(
        @PathVariable id: Int,
        @RequestBody json: User
    ):ResponseEntity<Any>{
        val newUser = userRepository.findById(id).orElseThrow { RuntimeException("User not found with $id") }

        newUser.id = json.id
        newUser.email = json.email
        newUser.name = json.name
        newUser.password = json.password

        userRepository.save(newUser)
        return ResponseEntity.ok(newUser)
    }

    @DeleteMapping("/user/delete/{id}")
    fun deleteUser(
        @PathVariable id: Int
    ):ResponseEntity<Any>{
        val deletedUser = userRepository.findById(id).orElseThrow { RuntimeException("User not found with $id") }

        userRepository.delete(deletedUser)
        return ResponseEntity.ok(deletedUser)
    }
}
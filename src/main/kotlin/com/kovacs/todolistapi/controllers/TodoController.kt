package com.kovacs.todolistapi.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kovacs.todolistapi.models.Task
import com.kovacs.todolistapi.repositories.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")
class TodoController (private val todoRepository: TodoRepository){

    @GetMapping("/task/list")
    fun getListTask(
        @RequestHeader token: String
    ): ResponseEntity<Any>{
        return if (validToken(token)){
            val tasks = todoRepository.getListTask()
            ResponseEntity.ok(tasks)
        }else{
            ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Acess Unathourized")
        }
    }

    @GetMapping("/task/{userid}/list")
    fun getTaskById(
        @PathVariable userid: Int
    ):ResponseEntity<Any>{
        val tasks = todoRepository.getTaskById(userid)

        return ResponseEntity.ok(tasks)
    }

    @PostMapping("/task/create")
    fun setTaskById(
    @RequestBody json: String
    ):ResponseEntity<Any>{
        val task = todoRepository.save(jacksonObjectMapper().readValue(json, Task::class.java))

        return ResponseEntity.ok(json)
    }

    fun validToken(token: String): Boolean{
        return token == "secret"
    }
}
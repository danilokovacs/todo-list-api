package com.kovacs.todolistapi.controllers

import com.kovacs.todolistapi.repositories.TodoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api")
class TodoController (private val todoRepository: TodoRepository){

    @GetMapping("/task/list")
    fun getListTask(): ResponseEntity<Any>{
        val tasks = todoRepository.getTask()

        return ResponseEntity.ok(tasks)
    }
}
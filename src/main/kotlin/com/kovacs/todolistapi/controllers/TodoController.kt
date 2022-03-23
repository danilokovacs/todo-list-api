package com.kovacs.todolistapi.controllers

import com.kovacs.todolistapi.repositories.TodoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")
class TodoController (private val todoRepository: TodoRepository){

    @GetMapping("/task/list")
    fun getListTask(): ResponseEntity<Any>{
        val tasks = todoRepository.getListTask()

        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/task/{userid}/list")
    fun getTaskById(
        @PathVariable userid: Int
    ):ResponseEntity<Any>{
        val tasks = todoRepository.getTaskById(userid)

        return ResponseEntity.ok(tasks)
    }
}
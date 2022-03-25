package com.kovacs.todolistapi.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kovacs.todolistapi.models.Task
import com.kovacs.todolistapi.repositories.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping("api")
class TaskController (private val taskRepository: TaskRepository){

    @GetMapping("/task/list")
    fun getListTask(
        @RequestHeader token: String
    ): ResponseEntity<Any>{
        return if (validToken(token)){
            val tasks = taskRepository.getListTask()
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
        val tasks = taskRepository.getTaskById(userid)

        return ResponseEntity.ok(tasks)
    }

    @PostMapping("/task/create")
    fun setTaskById(
    @RequestBody json: String
    ):ResponseEntity<Any>{
        val jsonBody = jacksonObjectMapper().readValue(json, Task::class.java)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val date = formatter.format(LocalDateTime.now())

        jsonBody.date_changed = date

        taskRepository.save(jsonBody)

        return ResponseEntity.ok(jsonBody)
    }

    @PutMapping("/task/update/{id}")
    fun putTask(
        @PathVariable id: Int,
        @RequestBody json: Task
    ):ResponseEntity<Any>{
        val updatedTask = taskRepository.findById(id).orElseThrow { RuntimeException("Task not found with $id") }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val date = formatter.format(LocalDateTime.now())

        updatedTask.resume = json.resume
        updatedTask.description = json.description
        updatedTask.status = json.status
        updatedTask.date_changed = date

        taskRepository.save(updatedTask)

        return ResponseEntity.ok(updatedTask)
    }

    @DeleteMapping("/task/delete/{id}")
    fun deleteTask(
        @PathVariable id: Int
    ):ResponseEntity<Any>{
        val deletedTask = taskRepository.findById(id).orElseThrow { RuntimeException("Task not found with $id") }

        taskRepository.delete(deletedTask)
        return ResponseEntity.ok(deletedTask)
    }


    fun validToken(token: String): Boolean{
        return token == "secret"
    }
}
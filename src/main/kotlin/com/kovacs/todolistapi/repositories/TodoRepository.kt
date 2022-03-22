package com.kovacs.todolistapi.repositories

import com.kovacs.todolistapi.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TodoRepository : JpaRepository <Task, Int> {

    @Query("SELECT t FROM Task t")
    fun getTask(): List<Task>

}
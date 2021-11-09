package com.em.tasktracker.controller;

import com.em.tasktracker.exception.ResourceNotFoundException;
import com.em.tasktracker.model.Task;
import com.em.tasktracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    //Get All Tasks API
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    //Create Task
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }

    //Get Task By Id
    @GetMapping("tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
       Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found!" + id));
       return ResponseEntity.ok().body(task);
    }
    //Update Task

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") long id, @RequestBody Task taskDetails) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found!" + id));
        task.setText(taskDetails.getText());
        task.setSched(taskDetails.getSched());
        taskRepository.save(task);
        return ResponseEntity.ok().body(task);
    }

    //Delete Task

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found!" + id));

        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

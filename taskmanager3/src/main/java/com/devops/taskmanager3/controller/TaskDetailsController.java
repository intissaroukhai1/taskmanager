package com.devops.taskmanager3.controller;

import com.devops.taskmanager3.model.Task;
import com.devops.taskmanager3.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task-details")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskDetailsController {

    private final TaskRepository taskRepository;

    public TaskDetailsController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ✅ Récupérer les détails d'une tâche
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskDetails(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

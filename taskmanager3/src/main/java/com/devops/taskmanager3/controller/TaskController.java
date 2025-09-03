package com.devops.taskmanager3.controller;

import com.devops.taskmanager3.model.Task;
import com.devops.taskmanager3.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.findById(id).map(existingTask -> {

            // ✅ Champs modifiables par l'utilisateur
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());

            existingTask.setAssignedTo(task.getAssignedTo());
            existingTask.setEstimatedTime(task.getEstimatedTime());
            if (!"Completed".equalsIgnoreCase(existingTask.getStatus())) {
                existingTask.setTimeSpent(task.getTimeSpent());
                existingTask.setStartTime(task.getStartTime());
            }




            // ✅ Ne pas changer createdBy (il doit rester celui qui a créé la tâche)
            // existingTask.setCreatedBy(task.getCreatedBy()); ❌ À éviter ici

            // ✅ updatedAt sera mis à jour automatiquement par @PreUpdate
            return ResponseEntity.ok(taskRepository.save(existingTask));
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskRepository.findById(id).map(task -> {
            taskRepository.delete(task);
            return ResponseEntity.noContent().<Void>build(); // ✅ type explicite
        }).orElse(ResponseEntity.notFound().build());

    }
}

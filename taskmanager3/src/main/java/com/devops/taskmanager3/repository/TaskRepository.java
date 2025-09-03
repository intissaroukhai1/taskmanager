package com.devops.taskmanager3.repository;


import com.devops.taskmanager3.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
    public interface TaskRepository extends JpaRepository<Task, Long> {
        // Tu peux ajouter des méthodes personnalisées ici si besoin
    }


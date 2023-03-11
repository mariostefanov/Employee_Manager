package com.example.employee_task_manager.service;

import com.example.employee_task_manager.entity.Task;
import com.example.employee_task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public boolean updateTitle(String[] commandParts) {
        long id = Long.parseLong(commandParts[2]);
        Optional<Task> optTask = this.taskRepository.findById(id);
        if (optTask.isPresent()){
            taskRepository.updateDueDate(LocalDate.parse(commandParts[4], DateTimeFormatter.ofPattern("d/M/yyyy")),id);
        } else {
            throw new EntityNotFoundException("Task doesn't exist.");
        }
        return false;
    }
}

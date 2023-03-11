package com.example.employee_task_manager.repository;

import com.example.employee_task_manager.entity.Employee;
import com.example.employee_task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task getTaskById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t set t.dueDate = :dueDate where t.id=:id")
    void updateDueDate(LocalDate dueDate, long id);
}

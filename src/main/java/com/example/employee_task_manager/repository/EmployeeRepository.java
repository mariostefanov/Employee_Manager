package com.example.employee_task_manager.repository;

import com.example.employee_task_manager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> getById(long id);

    @Modifying
    @Transactional
    @Query("Update Employee e set e.email=:email where e.id=:id")
    void updateEmail(String email,long id);
}

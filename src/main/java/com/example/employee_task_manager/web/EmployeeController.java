package com.example.employee_task_manager.web;

import com.example.employee_task_manager.entity.Employee;
import com.example.employee_task_manager.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

//@Controller
//@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee( @RequestBody Employee employee) {
        Employee savedUser = employeeRepository.save(employee);
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).body(savedUser);
    }

}

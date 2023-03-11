package com.example.employee_task_manager.service;

import com.example.employee_task_manager.entity.Employee;
import com.example.employee_task_manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void updateEmail(String[] commandParts) {
        long id = Long.parseLong(commandParts[2]);
        Optional<Employee> employeeOpt = this.employeeRepository.findById(id);

        if (employeeOpt.isPresent()){
           this.employeeRepository.updateEmail(commandParts[4],id);
        } else {
            throw new EntityNotFoundException("Employee doesn't exist.");
        }
    }
}

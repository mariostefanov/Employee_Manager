package com.example.employee_task_manager.service;

import com.example.employee_task_manager.entity.Employee;
import com.example.employee_task_manager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String EMPLOYEE_FILE_PATH = "src/main/resources/files/employees.txt";
    private final EmployeeRepository employeeRepository;

    public SeedServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void seedEmployees() throws IOException {
        Files.readAllLines(Path.of(EMPLOYEE_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getEmployeeObject)
                .forEach(employeeRepository::save);
    }

    private Employee getEmployeeObject(String line) {
        String[] employeeParts = line.split("\\|");

        LocalDate dateOfBirth =
                LocalDate.parse(employeeParts[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
        BigDecimal salary = new BigDecimal(employeeParts[4]);

        return new Employee(employeeParts[0],employeeParts[1],employeeParts[2],dateOfBirth,salary);
    }
}

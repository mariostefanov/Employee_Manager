package com.example.employee_task_manager.service;

import com.example.employee_task_manager.entity.Employee;
import com.example.employee_task_manager.entity.Task;
import com.example.employee_task_manager.repository.EmployeeRepository;
import com.example.employee_task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String COMMON_FILE_PATH = "src/main/resources/files";
    private static final String EMPLOYEE_FILE_PATH = COMMON_FILE_PATH + "/employees.txt";
    private static final String TASK_FILE_PATH = COMMON_FILE_PATH + "/tasks.txt";
    private final EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;

    public SeedServiceImpl(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void seedEmployees() throws IOException {
        Files.readAllLines(Path.of(EMPLOYEE_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getEmployeeObject)
                .forEach(employeeRepository::save);
    }

    @Override
    public void seedTasks() throws IOException {
        Files.readAllLines(Path.of(TASK_FILE_PATH))
                .stream()
                .filter(s->!s.isBlank())
                .map(this::getTaskObject)
                .forEach(taskRepository::save);
    }

    private Task getTaskObject(String line) {
        String[] taskParts = line.split("\\|");
        LocalDate dueDate = LocalDate
                .parse(taskParts[3],DateTimeFormatter.ofPattern("d/M/yyyy"));
        if (dueDate.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("The due date must be present or future");
        }
        Optional<Employee> taskOpt = employeeRepository.getById(Long.parseLong(taskParts[2]));
        if (employeeRepository.getById(Long.parseLong(taskParts[2])).isPresent()){
            return new Task(taskParts[0],taskParts[1], taskOpt.get(),dueDate);
        }else {
            throw new EntityNotFoundException("Employee not found!");
        }
    }

    private Employee getEmployeeObject(String line) {
        String[] employeeParts = line.split("\\|");

        LocalDate dateOfBirth = LocalDate
                .parse(employeeParts[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
        BigDecimal salary = new BigDecimal(employeeParts[4]);

        return new Employee(employeeParts[0],employeeParts[1],employeeParts[2],dateOfBirth,salary);
    }
}

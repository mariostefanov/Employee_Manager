package com.example.employee_task_manager;

import com.example.employee_task_manager.repository.EmployeeRepository;
import com.example.employee_task_manager.repository.TaskRepository;
import com.example.employee_task_manager.service.EmployeeService;
import com.example.employee_task_manager.service.SeedService;
import com.example.employee_task_manager.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public ConsoleRunner(SeedService seedService, TaskRepository taskRepository, TaskService taskService, EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.seedService = seedService;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] commandParts = line.split("\\|");
            switch (commandParts[0]) {
                case "Create" -> seedService.seedFromConsole(line);
                case "Update" -> {
                    if (commandParts[1].equals("Task")) {
                        taskService.updateDueDate(commandParts);
                    } else if (commandParts[1].equals("Employee")) {
                        employeeService.updateEmail(commandParts);
                    }
                }
                case "Get best 5 employees" -> {
                    this.employeeRepository.getBestEmployees(LocalDate.now().minusMonths(1), LocalDate.now()).forEach(b -> System.out.printf("%s completed %d tasks in the past month.%n", b.getFullName(), b.getTasks().size()));
                    System.out.println();
                }
            }
            line = scanner.nextLine();
        }
    }
}

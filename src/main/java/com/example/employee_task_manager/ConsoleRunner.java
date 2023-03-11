package com.example.employee_task_manager;

import com.example.employee_task_manager.repository.TaskRepository;
import com.example.employee_task_manager.service.EmployeeService;
import com.example.employee_task_manager.service.SeedService;
import com.example.employee_task_manager.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private TaskRepository taskRepository;
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public ConsoleRunner(SeedService seedService, TaskRepository taskRepository, TaskService taskService, EmployeeService employeeService) {
        this.seedService = seedService;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedEmployees();
        //this.seedService.seedTasks();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] commandParts = line.split("\\|");
            switch (commandParts[0]) {
                case "Create":
                    seedService.seedFromConsole(line);
                    break;
                case "Update":
                    if (commandParts[1].equals("Task")) {
                        taskService.updateTitle(commandParts);
                    } else if (commandParts[1].equals("Employee")) {
                        employeeService.updateEmail(commandParts);
                    }
                    break;
            }

            line = scanner.nextLine();
        }
    }
}

package com.example.employee_task_manager;

import com.example.employee_task_manager.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;

    public ConsoleRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedEmployees();
    }
}

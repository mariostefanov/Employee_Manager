package com.example.employee_task_manager.service;

import java.io.IOException;

public interface SeedService {
    void seedEmployees() throws IOException;
    void seedTasks() throws IOException;

    void seedFromConsole(String line);

    void seedAll() throws IOException;
}

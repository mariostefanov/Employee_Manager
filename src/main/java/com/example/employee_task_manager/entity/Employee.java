package com.example.employee_task_manager.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private BigDecimal monthlySalary;

    @OneToMany(targetEntity = Task.class,mappedBy = "assignee",fetch = FetchType.EAGER)
    private List<Task> tasks;


    public Employee(String fullName, String email, String phoneNumber, LocalDate dateOfBirth, BigDecimal monthlySalary) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.monthlySalary = monthlySalary;
        this.tasks = new ArrayList<>();
    }

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public Employee setId(long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Employee setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Employee setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public Employee setMonthlySalary(BigDecimal monthlySalary) {
        this.monthlySalary = monthlySalary;
        return this;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Employee setTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    @Override
    public String toString() {
        return fullName;
    }
}

package com.thalesapp.controller;

import com.thalesapp.exception.EmployeeNotFoundException;
import com.thalesapp.model.Employee;
import com.thalesapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String listEmployees(Model model, @RequestParam(required = false) Long id) {
        List<Employee> employees;
        if (id == null || id <= 0) {
            employees = employeeService.getAllEmployees();
        } else {
            Employee employee = employeeService.getEmployeeById(id);
            employees = new ArrayList<>();
            if (employee != null) {
                employees.add(employee);
            }
        }
        model.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        model.addAttribute("employee", employee);
        return "employee-details";  
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(EmployeeNotFoundException.class)
        public String handleEmployeeNotFoundException(EmployeeNotFoundException e, Model model) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; 
        }
    }
}

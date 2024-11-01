package com.thalesapp.service;

import com.thalesapp.exception.EmployeeNotFoundException;
import com.thalesapp.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAnnualSalary() {
        Employee employee = new Employee(1L, "Tiger Nixon", "320800", "61", "");
        employeeService.calculateAnnualSalary(employee);
        assertEquals(3849600.0, employee.getEmployee_anual_salary());
    }

    @Test
    void testCalculateAnnualSalary_NullEmployee() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.calculateAnnualSalary(null);
        });
        assertEquals("Employee cannot be null", exception.getMessage());
    }
}

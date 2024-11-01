package com.thalesapp.service;

import com.thalesapp.exception.EmployeeNotFoundException;
import com.thalesapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        System.out.println("Fetching employees from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        headers.add("Accept", "application/json");
        headers.add("Cookie", "humans_21909=1");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<EmployeeResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, EmployeeResponse.class);
            EmployeeResponse employeeResponse = response.getBody();
            if (employeeResponse == null || employeeResponse.getData() == null) {
                throw new EmployeeNotFoundException("No employees found.");
            }
            else
            {
            	List<Employee> employees = employeeResponse.getData();
            	for (Employee employee : employees) {
            	    this.calculateAnnualSalary(employee);            	   
            	}
            }
            return employeeResponse.getData();
        } catch (Exception e) {
            System.out.println("Error fetching employees: " + e.getMessage());
            throw new EmployeeNotFoundException("Error fetching employees: " + e.getMessage());
        }
    }

    public Employee getEmployeeById(Long id) {
        String url = "http://dummy.restapiexample.com/api/v1/employee/" + id;
        System.out.println("Fetching employee from URL: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        headers.add("Accept", "application/json");
        headers.add("Cookie", "humans_21909=1");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<EmployeeDetailResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, EmployeeDetailResponse.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                EmployeeDetailResponse employeeResponse = response.getBody();
                if(employeeResponse != null)
                { 
                	this.calculateAnnualSalary(employeeResponse.getData());
                	return employeeResponse.getData();                	
                }
                else 
                { 
                	return null;
                }
            } else {
                throw new EmployeeNotFoundException("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error fetching employee: " + e.getMessage());
            throw new EmployeeNotFoundException("Error fetching employee with ID " + id + ": " + e.getMessage());
        }
    }
    
    public void calculateAnnualSalary(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        employee.setEmployee_anual_salary((Double.parseDouble(employee.getEmployee_salary()) * 12));
    }
}

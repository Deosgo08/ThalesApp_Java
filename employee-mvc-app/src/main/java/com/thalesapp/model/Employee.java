package com.thalesapp.model;

public class Employee {
    private Long id; // ID del empleado
    private String employee_name; // Nombre del empleado
    private String employee_salary; // Salario del empleado
    private String employee_age; // Edad del empleado
    private String profile_image; // Imagen de perfil
    private double employee_anual_salary; // Salario anual del empleado

    public Employee() {}

    public Employee(Long id, String employee_name, String employee_salary, String employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }

    public double getEmployee_anual_salary() {
        return employee_anual_salary;
    }

    public void setEmployee_anual_salary(double employee_anual_salary) {
        this.employee_anual_salary = employee_anual_salary;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getEmployeeAnualSalary() {
        return String.valueOf(employee_anual_salary); 
    }
}

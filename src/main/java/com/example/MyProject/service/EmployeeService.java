package com.example.MyProject.service;

import com.example.MyProject.model.Employee;
import com.example.MyProject.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee){
        employee.setId(UUID.randomUUID());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(UUID id, Employee employeeDetails){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null){
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
            employee.setRoles(employeeDetails.getRoles());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(UUID id){
        employeeRepository.deleteById(id);
    }
}

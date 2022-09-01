package com.huang.dao;

import com.huang.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAllEmployees();

    Employee getEmployee(int id);

    void saveEmployee(Employee employee);

    void updateEmployee(int id);

    void deleteEmployee(int id);
}

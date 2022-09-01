package com.huang;

import com.huang.dao.EmployeeDaoImpl;
import com.huang.entity.Employee;
import com.huang.view.EmployeeView;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        Scanner keyboard = new Scanner(System.in);

        try {
            int option;
            EmployeeView employeeView = new EmployeeView();
            EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

            while (true) {
                employeeView.showMainMenu();
                option = Integer.parseInt(keyboard.nextLine());

                if(option == 0) {
                    System.out.println("Exit program...");
                    System.exit(0);
                } else if (option == 1) {
                    // create a new employee
                    Employee newEmployee = employeeView.newEmployee();
                    System.out.println("Saving the employee to database...");
                    employeeDao.saveEmployee(newEmployee);
                } else if (option == 2) {
                    // get an employee by id
                    int id = employeeView.getEmployee();
                    System.out.println("Retrieve the employee...");
                    employeeDao.getEmployee(id);
                } else if (option == 3) {
                    // get all employees
                    System.out.println("Retrieve all employees...");
                    List<Employee> employees = employeeDao.getAllEmployees();
                    employeeView.showAll(employees);
                } else if (option == 4) {
                    // update an employee
                    int id = employeeView.getEmployee();
                    employeeDao.updateEmployee(id);
                } else if (option == 5) {
                    // delete an employee
                    int id = employeeView.getEmployee();
                    employeeDao.deleteEmployee(id);
                } else {
                    System.out.println("Invalid input!");
                }

                System.out.println("Transaction complete!");

                // Pause the program until user press Enter key
                System.out.print("\nPress \"Enter\" to continue..");
                keyboard.nextLine();
            }

        } finally {
            factory.close();
        }
    }
}

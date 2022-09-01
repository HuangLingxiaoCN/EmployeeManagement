package com.huang.view;

import com.huang.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeView {

    private final Scanner keyboard = new Scanner(System.in);

    public void showMainMenu(){
        System.out.println("Enter your choice (0 to exit program): \n");
        System.out.println("1. Create a new employee");
        System.out.println("2. Get an employee by id");
        System.out.println("3. Get all employees");
        System.out.println("4. Update an employee");
        System.out.println("5. Delete an employee");
    }

    public Employee newEmployee() {
        System.out.println("New employee first name: ");
        String firstName = keyboard.nextLine();
        System.out.println("New employee last name: ");
        String lastName = keyboard.nextLine();
        System.out.println("New employee company: ");
        String company = keyboard.nextLine();

        return new Employee(firstName, lastName, company);
    }

    public int getEmployee() {
        System.out.println("Give an employee id: ");
        return Integer.parseInt(keyboard.nextLine());
    }

    public Employee updateEmployee() {
        System.out.println("Employee new first name: ");
        String firstName = keyboard.nextLine();
        System.out.println("Employee new last name: ");
        String lastName = keyboard.nextLine();
        System.out.println("Employee new company: ");
        String company = keyboard.nextLine();

        return new Employee(firstName, lastName, company);
    }

    public void showAll(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

}

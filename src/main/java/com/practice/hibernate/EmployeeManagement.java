package com.practice.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;
import java.util.List;

public class EmployeeManagement {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        Scanner keyboard = new Scanner(System.in);

        try {
            // create an GUI tool to operate Create, Read, Update and Delete functions
            int option;

            while (true) {
                Session session = factory.getCurrentSession();
                session.beginTransaction();

                System.out.println("Enter your choice (0 to exit program): \n");
                System.out.println("1. Create a new employee");
                System.out.println("2. Get an employee by id");
                System.out.println("3. Get all employees");
                System.out.println("4. Update an employee");
                System.out.println("5. Delete an employee");

                option = Integer.parseInt(keyboard.nextLine());
                if (option == 0) {
                    System.out.println("Exit program...");
                    System.exit(0);
                } else if (option == 1) {
                    // create a new employee
                    String firstName, lastName, company;

                    System.out.println("Employee first name: ");
                    firstName = keyboard.nextLine();
                    System.out.println("Employee last name: ");
                    lastName = keyboard.nextLine();
                    System.out.println("Employee company: ");
                    company = keyboard.nextLine();

                    System.out.println("Creating employee object...");
                    Employee employee = new Employee(firstName, lastName, company);

                    System.out.println("Saving the employee to database...");
                    session.save(employee);

                } else if (option == 2) {
                    // get an employee by id
                    int id;

                    System.out.println("Give an employee id: ");
                    id = Integer.parseInt(keyboard.nextLine());

                    System.out.println("Retrieve the employee...");
                    Employee theEmployee = session.get(Employee.class, id);

                    if (theEmployee == null) {
                        System.out.println("The employee with given id does not exist!");
                    }

                    System.out.println(theEmployee.toString());

                } else if (option == 3) {
                    // get all employees
                    System.out.println("Retrieve all employees...");
                    List<Employee> employees = session.createQuery("from Employee").getResultList();

                    for (Employee e : employees) {
                        System.out.println(e.toString());
                    }

                } else if (option == 4) {
                    // update an employee
                    int id;
                    String firstName, lastName, company;

                    System.out.println("Give an employee id: ");
                    id = Integer.parseInt(keyboard.nextLine());

                    Employee theEmployee = session.get(Employee.class, id);

                    System.out.println("New employee first name: ");
                    keyboard.nextLine();
                    firstName = keyboard.nextLine();
                    System.out.println("New employee last name: ");
                    lastName = keyboard.nextLine();
                    System.out.println("New employee company: ");
                    company = keyboard.nextLine();

                    System.out.println("Updating the employee data...");
                    theEmployee.setFirstName(firstName);
                    theEmployee.setLastName(lastName);
                    theEmployee.setCompany(company);

                } else if (option == 5) {
                    // delete an employee
                    int id;

                    System.out.println("Give an employee id: ");
                    id = Integer.parseInt(keyboard.nextLine());

                    Employee theEmployee = session.get(Employee.class, id);

                    System.out.println("Delete the employee...");
                    session.delete(theEmployee);

                } else {
                    System.out.println("Invalid input!");
                }

                session.getTransaction().commit();

                System.out.println("Transaction complete!");

                System.out.print("\nPress \"Enter\" to continue..");
                keyboard.nextLine();
            }

        } finally {
            factory.close();
        }

    }
}

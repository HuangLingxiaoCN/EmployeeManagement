package com.huang.dao;

import com.huang.entity.Employee;
import com.huang.view.EmployeeView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
    private final EmployeeView employeeView = new EmployeeView();

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Employee> employees = session.createQuery("from Employee").getResultList();
        session.getTransaction().commit();
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        System.out.println("Retrieve the employee...");
        Employee theEmployee = session.get(Employee.class, id);

        if (theEmployee == null) System.out.println("The employee with given id does not exist!");
        System.out.println(theEmployee.toString());

        session.getTransaction().commit();
        return null;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        System.out.println("Saving the employee to database...");
        session.save(employee);

        session.getTransaction().commit();
    }

    @Override
    public void updateEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Employee theEmployee = session.get(Employee.class, id);
        if (theEmployee == null) System.out.println("The employee with given id does not exist!");

        Employee newEmployee = employeeView.updateEmployee();
        System.out.println("Updating the employee data...");
        theEmployee.setFirstName(newEmployee.getFirstName());
        theEmployee.setLastName(newEmployee.getLastName());
        theEmployee.setCompany(newEmployee.getCompany());

        session.getTransaction().commit();
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Employee theEmployee = session.get(Employee.class, id);
        if (theEmployee == null) System.out.println("The employee with given id does not exist!");

        System.out.println("Delete the employee...");
        session.delete(theEmployee);

        session.getTransaction().commit();
    }
}

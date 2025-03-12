package com.ebeddard.ui;

import com.ebeddard.employee.Employee;
import com.ebeddard.employee.EmployeeService;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Menu {
    private static Scanner scan;
    private static EmployeeService employeeService;

    public static void mainMenu() {
        // once we enter menu, can initialize scanner
        scan = new Scanner(System.in);
        employeeService = new EmployeeService();

        System.out.println("Welcome to the Employee Management System!");

        // used to exit loop
        boolean exit = false;

        while (!exit) {

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Get Employee");
            System.out.println("5. List All Employees");
            System.out.println("6. Exit");

            int input = scan.nextInt();
            scan.nextLine();

            switch (input) {

                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    getEmployee();
                    break;
                case 5:
                    listAllEmployees();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("\nPlease enter an option listed (number 1 - 6)");
                    break;
            }
        }
    }

    private static void addEmployee() {
        System.out.println("Enter Employee First Name:");
        String fName = scan.nextLine();
        System.out.println("Enter Employee Last Name:");
        String lName = scan.nextLine();
        System.out.println("Enter Employee ID:");
        String empID = scan.nextLine();
        Date doe = null;
		System.out.println("Enter Date of Employment (yyyy-MM-dd):");
		while(doe == null) {
			String doeStr = scan.nextLine();
			try {
				doe = new SimpleDateFormat("yyyy-MM-dd").parse(doeStr);
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please use yyyy-MM-dd.");
			}
		}
        System.out.println("Enter Employee Salary:");
        int salary = scan.nextInt();
        scan.nextLine(); // consume newline
        System.out.println("Enter Employee Department:");
        String department = scan.nextLine();

        Employee employee = new Employee(fName, lName, empID, doe, salary, department);
        if (employeeService.addEmployee(employee)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Employee with this ID already exists.");
        }
    }

    private static void updateEmployee() {
        System.out.println("Enter Employee ID to update:");
        String empID = scan.nextLine();
        if (employeeService.updateEmployee(empID)) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void removeEmployee() {
        System.out.println("Enter Employee ID to remove:");
        String empID = scan.nextLine();
        if (employeeService.removeEmployee(empID)) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void getEmployee() {
        System.out.println("Enter Employee ID to get:");
        String empID = scan.nextLine();
        Employee employee = employeeService.getEmployee(empID);
        if (employee != null) {
            System.out.println("Employee Details: " + employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void listAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}

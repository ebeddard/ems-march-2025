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
		Employee employee = employeeService.getEmployee(empID);
		Employee updatedEmployee = new Employee();
        if (employee != null) {
			updatedEmployee.setEmpID(empID);
            System.out.println("Employee successfully found.");
			System.out.println("First Name:" + employee.getFName());
			System.out.println("Enter new First Name (leave blank for no update):");
			String fName = scan.nextLine();
			if (!fName.isBlank()) {
				updatedEmployee.setFName(fName);
			} else {
				updatedEmployee.setFName(employee.getFName());
			}
			System.out.println("Last Name:" + employee.getLName());
			System.out.println("Enter new Last Name (leave blank for no update):");
			String lName = scan.nextLine();
			if (!lName.isBlank()) {
				employee.setLName(lName);
			} else {
				updatedEmployee.setLName(employee.getLName());
			}
			System.out.println("Date of Employment:" + employee.getDoe());
			System.out.println("Enter new Date of Employment (yyyy-MM-dd) (leave blank for no update):");
			String doeStr = scan.nextLine();
			if (!doeStr.isBlank()) {
				try {
					Date doe = new SimpleDateFormat("yyyy-MM-dd").parse(doeStr);
					updatedEmployee.setDoe(doe);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please use yyyy-MM-dd.");
				}
			} else {
				updatedEmployee.setDoe(employee.getDoe());
			}
			System.out.println("Salary:" + employee.getSalary());
			System.out.println("Enter new Salary (leave blank for no update):");
			String salaryStr = scan.nextLine();
			if (!salaryStr.isBlank()) {
				int salary = Integer.parseInt(salaryStr);
				updatedEmployee.setSalary(salary);
			} else {
				updatedEmployee.setSalary(employee.getSalary());
			}
			System.out.println("Department:" + employee.getDepartment());
			System.out.println("Enter new Department (leave blank for no update):");
			String department = scan.nextLine();
			if (!department.isBlank()) {
				updatedEmployee.setDepartment(department);
			} else {
				updatedEmployee.setDepartment(employee.getDepartment());
			}
			System.out.println("Old Employee Details: " + employee);
			System.out.println("New Employee Details: " + updatedEmployee);
			String confirm = "n";
			System.out.println("Please type 'y' to confirm update or any other key to cancel:");
			confirm = scan.nextLine();
			if (!confirm.equalsIgnoreCase("y")) {
				System.out.println("Update cancelled.");
				return;
			}
			if (employeeService.updateEmployee(updatedEmployee)) {
				System.out.println("Employee updated successfully.");
			} else {
				System.out.println("Unable to update employee.");
			}

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
		System.out.println("Press enter to continue.");
		scan.nextLine();
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
		System.out.println("Press enter to continue.");
		scan.nextLine();
    }
}

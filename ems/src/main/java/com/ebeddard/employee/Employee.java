package com.ebeddard.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Employee {
    private String fName; // first name
    private String lName; // last name
    private String empID; // employee id
    private Date doe; // date of employment
    private int salary;
    private String department;

    public Employee() {
    }

    public Employee(String fName, String lName, String empID, Date doe, int salary, String department) {
        this.fName = fName;
        this.lName = lName;
        this.empID = empID;
        this.doe = doe;
        this.salary = salary;
        this.department = department;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmpID() {
        return this.empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Date getDoe() {
        return this.doe;
    }

    public void setDoe(Date doe) {
        this.doe = doe;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(fName, employee.fName) && Objects.equals(lName, employee.lName) && Objects.equals(empID, employee.empID) && Objects.equals(doe, employee.doe) && salary == employee.salary && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, empID, doe, salary, department);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return 
            "Name: '" + getFName() + " " + getLName() + "'\n" +
            "Employee ID: '" + getEmpID() + "'\n" +
            "Date of Employment: " + dateFormat.format(getDoe()) + "\n" +
            "Salary: " + getSalary() + "\n" +
            "Department: " + getDepartment() + "\n" +
            "========================";
    }
}

package com.ebeddard;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import com.ebeddard.employee.Employee;
import com.ebeddard.employee.EmployeeDao;
import com.ebeddard.employee.EmployeeService;

/**
 * Unit test
 */
public class MainTest 
{
    private EmployeeDao service;
    private Employee testEmployee;

    @Before
    public void setUp() throws ParseException {
        service = new EmployeeService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date doe = dateFormat.parse("2020-04-22");
        testEmployee = new Employee("John", "Doe", "12345", doe, 50000, "Engineering");
    }

    @Test
    public void addEmployeeTest() {
        service.addEmployee(testEmployee);
        assertTrue(service.getAllEmployees().contains(testEmployee));
    }

    @Test
    public void updateEmployeeTest() {
        service.addEmployee(testEmployee);
        Employee updatedEmployee = new Employee("John", "Doe", "12345", new Date(), 60000, "HR");
        boolean result = service.updateEmployee(updatedEmployee);
        assertTrue(result);
        assertTrue(service.getAllEmployees().contains(updatedEmployee));
        assertFalse(service.getAllEmployees().contains(testEmployee));
    }

    @Test
    public void removeEmployeeTest() {
        service.addEmployee(testEmployee);
        boolean result = service.removeEmployee(testEmployee.getEmpID());
        assertTrue(result);
        assertFalse(service.getAllEmployees().contains(testEmployee));
    }

    @Test
    public void getEmployeeTest() {
        service.addEmployee(testEmployee);
        Employee retrievedEmployee = service.getEmployee(testEmployee.getEmpID());
        assertEquals(testEmployee, retrievedEmployee);
    }

    @Test
    public void getNonExistentEmployeeTest() {
        Employee retrievedEmployee = service.getEmployee("nonexistentID");
        assertNull(retrievedEmployee);
    }

    @Test
    public void listAllEmployeesTest() {
        service.addEmployee(testEmployee);
        assertEquals(1, service.getAllEmployees().size());
        assertTrue(service.getAllEmployees().contains(testEmployee));
    }
}

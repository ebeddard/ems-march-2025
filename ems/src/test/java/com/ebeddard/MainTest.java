package com.ebeddard;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.ebeddard.employee.Employee;
import com.ebeddard.employee.EmployeeDao;
import com.ebeddard.employee.EmployeeService;

/**
 * Unit test
 */
public class MainTest 
{
    
    @Test
    public void addEmployeeTest()
    {
        EmployeeDao service = new EmployeeService();
        Employee testEmployee = new Employee("f", "l", 
        "12345", new Date(), 50000, "d");
        service.addEmployee(testEmployee);
        assertTrue(service.getAllEmployees().contains(testEmployee));
    }
}

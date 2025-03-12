package com.ebeddard.employee;

import java.util.List;

public interface EmployeeDao {
    Boolean addEmployee(Employee e);
    Boolean updateEmployee(Employee e);
    Boolean removeEmployee(String id);
    Employee getEmployee(String id);
    List<Employee> getAllEmployees();

}

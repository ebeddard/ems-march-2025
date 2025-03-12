package com.ebeddard.employee;

import java.util.List;

public interface EmployeeDao {
    Boolean addEmployee(Employee e);
    Boolean updateEmployee(String id);
    Boolean removeEmployee(String id);
    Employee getEmployee(String id);
    List<Employee> getAllEmployees();
}

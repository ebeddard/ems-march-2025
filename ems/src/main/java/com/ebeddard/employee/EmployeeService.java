package com.ebeddard.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService implements EmployeeDao {
    // a map linking employee IDs to employeesa
    private Map<String, Employee> empMap = new HashMap<>();

    @Override
    public Boolean addEmployee(Employee e) {
        if(!empMap.containsKey(e.getEmpID())) { // 1 employee per ID
            empMap.put(e.getEmpID(), e);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateEmployee(Employee e) {
        if(empMap.containsKey(e.getEmpID())) {
            empMap.put(e.getEmpID(), e);    // add updated employee
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeEmployee(String id) {
        if(empMap.containsKey(id)) {
            empMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Employee getEmployee(String id) {
        return empMap.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empMap.values().stream().collect(Collectors.toList());
    }
}

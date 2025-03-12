package com.ebeddard.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService implements EmployeeDao {
    // a map linking employee IDs to employeesa
    private Map<String, Employee> empIDMap = new HashMap<>();

    @Override
    public Boolean addEmployee(Employee e) {
        if(!empIDMap.containsKey(e.getEmpID())) { // won't add duplicates
            empIDMap.put(e.getEmpID(), e);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateEmployee(String id) {
        if(empIDMap.containsKey(id)) {
            empIDMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeEmployee(String id) {
        if(empIDMap.containsKey(id)) {
            empIDMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Employee getEmployee(String id) {
        return empIDMap.get(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empIDMap.values().stream().collect(Collectors.toList());
    }
}

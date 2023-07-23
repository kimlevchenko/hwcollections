package pro.sky.java.group.hwcollections.service;

import org.springframework.stereotype.Service;
import pro.sky.java.group.hwcollections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeNotFoundException;
import pro.sky.java.group.hwcollections.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.group.hwcollections.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 10;
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(String firstName, String lastName) {
        if (employees.size() == SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        var employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
    }

    public Employee findEmployee(String firstName, String lastName) {
        var employee = new Employee(firstName, lastName);
        for (Employee emp : employees) {
            if (emp.equals(employee)) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public boolean removeEmployee(String firstName, String lastName) {
        var employee = new Employee(firstName, lastName);
        if (employees.remove(employee)) {
            return true;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return employees;
    }
}

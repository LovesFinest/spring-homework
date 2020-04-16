package homework;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeDao {

    private static final List<Employee> EMPLOYEES = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong COUNTER = new AtomicLong();

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(EMPLOYEES);
    }

    public Employee saveEmployee(String name) {
        var employee = new Employee(COUNTER.incrementAndGet(), name);

        EMPLOYEES.add(employee);

        return employee;
    }

    public Employee findEmployeeById(long id) {
        return EMPLOYEES.stream().filter(emp -> emp.getId() == id).findAny().orElseThrow(() -> new NoSuchElementException("Employee with id: '" + id + "' is not in the Database"));
    }

    public Employee updateEmployee(long id, String name) {
        var oldEmployee = findEmployeeById(id);
        var newEmployee = new Employee(id, name);

        Collections.replaceAll(EMPLOYEES, oldEmployee, newEmployee);

        return newEmployee;
    }

    public void deleteEmployee(long id) {
        EMPLOYEES.remove(findEmployeeById(id));
    }

    public void emptyEmployees() {
        EMPLOYEES.clear();
    }
}

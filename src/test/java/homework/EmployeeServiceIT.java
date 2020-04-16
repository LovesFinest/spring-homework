package homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeConfig.class)
public class EmployeeServiceIT {

    @Autowired
    private EmployeeService service;

    private Employee currentEmployee;

    @BeforeEach
    void setUp() {
        service.emptyEmployees();
        currentEmployee = service.saveEmployee("Sub-Zero");
    }

    @Test
    void saveEmployee() {
        assertEquals(List.of(currentEmployee), service.getEmployees());
    }

    @Test
    void findEmployeeById() {
        assertEquals(currentEmployee, service.findEmployeeById(currentEmployee.getId()));
    }

    @Test
    void updateEmployee() {
        var updatedName = "Sonya";

        assertEquals(updatedName.toUpperCase(), service.updateEmployee(currentEmployee.getId(), updatedName).getName());
    }

    @Test
    void deleteEmployee() {
        service.deleteEmployee(currentEmployee.getId());

        assertThrows(NoSuchElementException.class, () -> service.findEmployeeById(currentEmployee.getId()));
    }
}

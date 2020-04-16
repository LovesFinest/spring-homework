package homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoTest {

    private final EmployeeDao dao = new EmployeeDao();

    private Employee currentEmployee;

    @BeforeEach
    void setUp() {
        dao.emptyEmployees();
        currentEmployee = dao.saveEmployee("Scorpion");
    }

    @Test
    void getEmployees() {
        assertEquals(List.of(currentEmployee), dao.getEmployees());
    }

    @Test
    void saveEmployee() {
        assertEquals(List.of(currentEmployee), dao.getEmployees());
    }

    @Test
    void findEmployeeById() {
        assertEquals(currentEmployee, dao.findEmployeeById(currentEmployee.getId()));
    }

    @Test
    void findEmployeeByIdWithInvalidId() {
        assertThrows(NoSuchElementException.class, () -> dao.findEmployeeById(9999L));
    }

    @Test
    void updateEmployee() {
        var updatedName = "Sonya";

        assertEquals(updatedName, dao.updateEmployee(currentEmployee.getId(), updatedName).getName());
    }

    @Test
    void deleteEmployee() {
        dao.deleteEmployee(currentEmployee.getId());

        assertTrue(dao.getEmployees().isEmpty());
    }

    @Test
    void emptyEmployees() {
        dao.emptyEmployees();

        assertTrue(dao.getEmployees().isEmpty());
    }
}

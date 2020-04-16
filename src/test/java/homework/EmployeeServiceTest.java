package homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDao dao;

    @Mock
    private ApplicationEventPublisher publisher;

    private EmployeeService service;
    private Employee currentEmployee;

    @BeforeEach
    void setUp() {
        service = new EmployeeService(dao, publisher, true);
    }

    @Test
    void getEmployees() {
        service.getEmployees();

    }

    @Test
    void saveEmployee() {
        service.saveEmployee("Sonya");

        verify(dao).saveEmployee(eq("SONYA"));
        verify(dao, never()).getEmployees();
    }

    @Test
    void saveEmployeeWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> service.saveEmployee("     "));
    }

    @Test
    void saveEmployeeWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> service.saveEmployee(null));
    }

    @Test
    void findEmployeeById() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void emptyEmployees() {
    }
}
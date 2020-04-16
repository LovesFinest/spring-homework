package homework;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeDao dao;
    private final ApplicationEventPublisher publisher;
    private final boolean isUppercase;

    public EmployeeService(EmployeeDao dao, ApplicationEventPublisher publisher, @Value("${uppercase.enabled}") boolean isUppercase) {
        this.dao = dao;
        this.publisher = publisher;
        this.isUppercase = isUppercase;
    }

    @PostConstruct
    private void init() {
        log.info("service created");
    }

    public List<Employee> getEmployees() {
        return dao.getEmployees();
    }

    public Employee saveEmployee(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be empty");
        }

        var convertedName = convertName(name);

        publisher.publishEvent(new EmployeeHasCreatedEvent(this, convertedName));

        return dao.saveEmployee(convertedName);
    }

    public Employee findEmployeeById(long id) {
        return dao.findEmployeeById(id);
    }

    public Employee updateEmployee(long id, String name) {
        return dao.updateEmployee(id, convertName(name));
    }

    public void deleteEmployee(long id) {
        dao.deleteEmployee(id);
    }

    public void emptyEmployees() {
        dao.emptyEmployees();
    }

    private String convertName(String name) {
        return isUppercase ? name.toUpperCase() : name.toLowerCase();
    }
}

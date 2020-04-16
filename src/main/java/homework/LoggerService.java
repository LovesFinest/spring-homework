package homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class LoggerService implements ApplicationListener<EmployeeHasCreatedEvent> {

    @Override
    public void onApplicationEvent(EmployeeHasCreatedEvent event) {
        log.info("Event has arrived at: {}, with name: {}", LocalDateTime.now(), event.getName());
    }
}

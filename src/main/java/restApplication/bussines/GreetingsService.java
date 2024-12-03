package restApplication.bussines;

import org.slf4j.Logger;
import restApplication.data.Employee;

public class GreetingsService implements GreetingsServiceInterface {
    private Logger log;

    public GreetingsService(Logger log) {
        this.log = log;
    }

    @Override
    public void sayHello(Employee employee) {
        log.info("Hello " + employee.getFirstName());
    }
}

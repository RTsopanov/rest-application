package restApplication.bussines;

import org.slf4j.Logger;
import restApplication.data.Employee;

public class GreetingsService2 implements GreetingsServiceInterface {
    private Logger log;

    public GreetingsService2(Logger log) {
        this.log = log;
    }

    @Override
    public void sayHello(Employee employee) {
        log.info("Hello from test version " + employee.getFirstName());
    }
}

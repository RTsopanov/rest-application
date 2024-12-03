package restApplication.controllers.v1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import restApplication.data.Employee;
import restApplication.repository.EmployeeRepository;

@RestController("/v1/employees")
@Slf4j
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    Iterable<Employee> getAll() {
        return employeeRepository.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Employee newEmployee(@RequestBody Employee employee) {
        log.info("------------------------------------");
        if (employee.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Found ID. Use PUT instead of POST");
        }
        return employeeRepository.save(employee);
    }


    @GetMapping("/{id}")
    Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('WRITER')")
    void deleteById(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

}

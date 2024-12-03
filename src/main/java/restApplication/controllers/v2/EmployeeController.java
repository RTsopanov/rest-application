package restApplication.controllers.v2;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import restApplication.data.Employee;
import restApplication.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController("/v2/employees")
@Slf4j
@RequestMapping("/v2/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    Iterable<Employee> getAll(@RequestParam(required = false) String firstName) {
        if (firstName == null) {
            return employeeRepository.findAll();
        }
        return employeeRepository.findAllByFirstName(firstName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "newEmployee", summary = "Create new employee")
    Employee newEmployee(@RequestBody Employee employee) {
        log.info("------------------------------------");
        if (employee.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Found ID. Use PUT instead of POST");
        }
        return employeeRepository.save(employee);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('WRITER')")
    Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }





//    -----------------------------

    @GetMapping("/subordinates/{bossId}")
    public List<Employee> getSubordinates(@PathVariable Long bossId) {
        return employeeRepository.findSubordinatesByBossId(bossId);
    }


@GetMapping("/{employeeId}/boss")
public Employee getBoss(@PathVariable Long employeeId) {
    return employeeRepository.findById(employeeId)
            .map(employee -> {
                Optional<Employee> boss = employeeRepository.findById(employee.getBoss().getId());
                return boss.orElseThrow(() -> new EntityNotFoundException("Boss not found for employee with id: " + employeeId));
            })
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
}


    // TODO  задание №3
//    @GetMapping("/subordinates-higher-salary")
//    public ResponseEntity<List<Employee>> getSubordinatesWithHigherSalary() {
//        List<Employee> employees = employeeRepository.findSubordinatesWithHigherSalary();
//        return ResponseEntity.ok(employees);
//    }



}

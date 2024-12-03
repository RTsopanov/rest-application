package restApplication.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restApplication.data.Department;
import restApplication.data.Employee;
import restApplication.dtoNew.mapper.EmployeeMapper;
import restApplication.repository.DepartmentRepository;


import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Optional<restApplication.dtoNew.Employee> maxSalaryEmployeeInDep(Long departmentId) {
        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);
        if (departmentOpt.isEmpty() || departmentOpt.get().getEmployees().isEmpty()) {
            return Optional.empty();
        }
        Employee employee = departmentOpt.get().getEmployees().stream()
                .max((e1, e2) -> e1.getMonthSalary().compareTo(e2.getMonthSalary())).get();
        return Optional.of(employeeMapper.toDto(employee));
    }
}


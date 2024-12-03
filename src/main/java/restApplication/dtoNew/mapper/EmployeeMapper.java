package restApplication.dtoNew.mapper;


import org.mapstruct.Mapper;
import restApplication.dtoNew.Employee;

@Mapper
public interface EmployeeMapper {
    Employee toDto(restApplication.data.Employee employee);
}

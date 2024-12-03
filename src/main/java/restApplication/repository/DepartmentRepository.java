package restApplication.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restApplication.data.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}

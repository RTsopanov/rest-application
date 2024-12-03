package restApplication.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

// TODO  задание №3 - пробовал выполнить через JPA
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import restApplication.data.Employee;


import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    //public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findAllByFirstName(String firstName);

    //   ДЗ
    @Query("SELECT e FROM Employee e WHERE e.\"boss_ID\" = :bossId")
    List<Employee> findSubordinatesByBossId(Long bossId);


    Optional<Employee> findById(Long id);


    // TODO  задание №3 "получить список сотрудников, чья месячная заработная плата непосредственно больше, чем у их руководителей"
    //     не удалось выявить причину ошибки

//    @Query("SELECT sub FROM Employee sub JOIN sub.boss boss WHERE sub.monthSalary > boss.monthSalary")
//    List<Employee> findSubordinatesWithHigherSalary();


}

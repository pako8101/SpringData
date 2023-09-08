package bg.softuni.modelmapperlab.repository;

import bg.softuni.modelmapperlab.DTO.EmployeeNameAndSalaryDTO;
import bg.softuni.modelmapperlab.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
@Query("select  new bg.softuni.modelmapperlab.DTO.EmployeeNameAndSalaryDTO" +
        " (e.firstname, e .salary) from Employee  e where e.id = :id")
    Optional<EmployeeNameAndSalaryDTO> findEmployeeNameAndSalaryById(Long id);

}

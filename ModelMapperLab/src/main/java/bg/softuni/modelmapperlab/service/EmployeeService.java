package bg.softuni.modelmapperlab.service;

import bg.softuni.modelmapperlab.DTO.CreateEmployeeDTO;
import bg.softuni.modelmapperlab.DTO.EmployeeNameAndSalaryDTO;
import bg.softuni.modelmapperlab.model.Employee;

import java.util.Optional;

public interface EmployeeService {

    Employee create(CreateEmployeeDTO createEmployeeDTO);
    Optional<EmployeeNameAndSalaryDTO> getEmployeeNameAndSalaryById(Long id);
}

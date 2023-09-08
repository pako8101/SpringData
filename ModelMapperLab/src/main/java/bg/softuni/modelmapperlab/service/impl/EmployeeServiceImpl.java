package bg.softuni.modelmapperlab.service.impl;

import bg.softuni.modelmapperlab.DTO.CreateEmployeeDTO;
import bg.softuni.modelmapperlab.DTO.EmployeeNameAndSalaryDTO;
import bg.softuni.modelmapperlab.model.Employee;
import bg.softuni.modelmapperlab.repository.EmployeeRepository;
import bg.softuni.modelmapperlab.service.EmployeeService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final  ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(CreateEmployeeDTO createEmployeeDTO) {

//        PropertyMap<CreateEmployeeDTO, Employee> employeeMap = new PropertyMap<CreateEmployeeDTO, Employee>() {
//            @Override
//            protected void configure() {
//                String birthDayString = source.getBirthday() ;
//                if (birthDayString!= null) {
//                    LocalDate birthday = LocalDate.parse(birthDayString);
//                    map().setBirthday(birthday);
//                }
//            }
//        };
//        Converter<String,LocalDate> toLocalDate = new AbstractConverter<String, LocalDate>() {
//            @Override
//            protected LocalDate convert(String source) {
//                LocalDate result = null;
//                if (source!=null){
//                    result = LocalDate.parse(source);
//                }
//                return result;
//            }
//        };
//
//       modelMapper.addConverter(toLocalDate);
         Employee employee = modelMapper.map(createEmployeeDTO, Employee.class);
       employee =  employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Optional<EmployeeNameAndSalaryDTO> getEmployeeNameAndSalaryById(Long id) {
        return employeeRepository.findEmployeeNameAndSalaryById(id);
    }
}

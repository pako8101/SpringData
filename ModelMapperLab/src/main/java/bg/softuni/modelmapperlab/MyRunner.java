package bg.softuni.modelmapperlab;

import bg.softuni.modelmapperlab.DTO.CreateAddressDTO;
import bg.softuni.modelmapperlab.DTO.CreateEmployeeDTO;
import bg.softuni.modelmapperlab.model.Address;
import bg.softuni.modelmapperlab.model.Employee;
import bg.softuni.modelmapperlab.service.AddressService;
import bg.softuni.modelmapperlab.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    private final AddressService addressService;
    private final EmployeeService employeeService;

    public MyRunner(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Miro - ");
        Scanner scanner = new Scanner(System.in);
        //  firstModelMappingExample(scanner);
//        saveToDB(scanner);
showEmployee(scanner);
    }

    private void showEmployee(Scanner scanner) {
        System.out.println("Employee nam and salary = " + employeeService.getEmployeeNameAndSalaryById(Long.valueOf(6)).get());

    }

    private void saveToDB(Scanner scanner) {
        System.out.println("Enter country = ");
        String country = scanner.nextLine();

        System.out.println("Enter city = ");
        String city = scanner.nextLine();


        CreateAddressDTO createAddressDTO = new CreateAddressDTO(country, city);

        System.out.println("createAddressDTO = " + createAddressDTO);

        Address address = addressService.create(createAddressDTO);
        System.out.println("address = " + address);

        System.out.println("Enter firstNAme = ");
        String firstName = scanner.nextLine();

        System.out.println("Enter salary = ");
        String salary = scanner.nextLine();
        System.out.println("Enter birthday = ");
        String birthday = scanner.nextLine();


        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(firstName, salary, birthday, createAddressDTO);
        Employee employee = employeeService.create(createEmployeeDTO);
        System.out.println("Employee = " + employee);
    }

    private static void firstModelMappingExample(Scanner scanner) {
        System.out.println("Enter country = ");
        String country = scanner.nextLine();

        System.out.println("Enter city = ");
        String city = scanner.nextLine();


        CreateAddressDTO createAddressDTO = new CreateAddressDTO("Bulgaria", "Sofia");

        System.out.println("createAddressDTO = " + createAddressDTO);

//        Address address = new Address();
//        address.setCountry(createAddressDTO.getCountry());
//        address.setCity(createAddressDTO.getCity());
//        System.out.println("address = " + address);

        ModelMapper modelMapper = new ModelMapper();
        Address address = modelMapper.map(createAddressDTO, Address.class);
        System.out.println("address = " + address);
        CreateAddressDTO createAddressDTO1 = modelMapper.map(address, CreateAddressDTO.class);
        System.out.println("createAddressDTO1 = " + createAddressDTO1);
    }
}

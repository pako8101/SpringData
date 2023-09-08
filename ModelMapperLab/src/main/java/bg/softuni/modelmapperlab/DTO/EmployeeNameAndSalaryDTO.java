package bg.softuni.modelmapperlab.DTO;

import java.math.BigDecimal;

public class EmployeeNameAndSalaryDTO {

    private String firstname;
    private BigDecimal salary;

    public EmployeeNameAndSalaryDTO() {
    }

    public EmployeeNameAndSalaryDTO(String firstname, BigDecimal salary) {
        this.firstname = firstname;
        this.salary = salary;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

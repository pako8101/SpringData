package bg.softuni.modelmapperlab.DTO;

public class CreateEmployeeDTO {

    private String firstName;
    private String salary;

    private String birthday;

    private CreateAddressDTO address;

    public CreateEmployeeDTO() {
    }

    public CreateEmployeeDTO(String firstName, String salary, String birthday, CreateAddressDTO address) {
        this.firstName = firstName;
        this.salary = salary;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public CreateAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CreateAddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDTO{" +
                "firstName='" + firstName + '\'' +
                ", salary='" + salary + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address=" + address +
                '}';
    }
}

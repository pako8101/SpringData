package dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    @Expose
    private String firstName;
      @Expose
    private String lastName;
     @Expose
    private int age;
     @Expose
     private StudentAdditionalInfo studentAdditionalInfo;

    @Expose

    private List<CourseDto> courses;


    public Student(String firstName, String lastName, int age,StudentAdditionalInfo studentAdditionalInfo, List<CourseDto> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.courses = courses;
        this.studentAdditionalInfo = studentAdditionalInfo;
    }

    @Override
    public String toString() {
        return "dtos.Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", additionalInfo =" + studentAdditionalInfo +
                ", courses =" + courses +
                '}';
    }
}

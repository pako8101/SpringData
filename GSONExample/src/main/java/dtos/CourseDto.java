package dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.Date;

public class CourseDto {
    @Expose
    private String name;
    @Expose
    private int lengthWeeks;
    @Expose
private Date createdAt;


    public CourseDto(String name, int lengthWeeks,Date createdAt) {
        this.name = name;
        this.lengthWeeks = lengthWeeks;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthWeeks() {
        return lengthWeeks;
    }

    public void setLengthWeeks(int lengthWeeks) {
        this.lengthWeeks = lengthWeeks;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "name='" + name + '\'' +
                ", lengthWeeks=" + lengthWeeks +
                ", createdAt=" + createdAt +
                '}';
    }
}

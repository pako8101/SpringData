package dtos;

import com.google.gson.annotations.Expose;

public class StudentAdditionalInfo {

    @Expose
    private  boolean isGraduated;
    @Expose
    private double averageGrade;

    public StudentAdditionalInfo(boolean isGraduated, double averageGrade) {
        this.isGraduated = isGraduated;
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "dtos.StudentAdditionalInfo{" +
                "isGraduated=" + isGraduated +
                ", averageAge=" + averageGrade +
                '}';
    }
}

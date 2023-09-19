import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CourseDto;
import dtos.Student;
import dtos.StudentAdditionalInfo;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainToJson {
    public static void main(String[] args) {
        final Gson gson = new GsonBuilder()
               .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

       Student student = new Student("first", null,22,
               new StudentAdditionalInfo( false,5.38),

               List.of(new CourseDto("MAth",20, Date.from(Instant.now())),
                       new CourseDto("Biology",30,  Date.from(Instant.now()))));
       String json = gson.toJson(student);

        System.out.println(json);

        List<Student> studentList = List.of(student);
        String jsonList = gson.toJson(studentList);
        System.out.println(jsonList);
    }
}

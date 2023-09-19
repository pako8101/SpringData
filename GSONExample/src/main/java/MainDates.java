import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CourseDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class MainDates {
    public static void main(String[] args) {

        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
               .setDateFormat("YYYY-MM-dd")
                .setPrettyPrinting()
                .create();
        final CourseDto math = new CourseDto("Math", 10, Date.from(Instant.now()));

        System.out.println(gson.toJson(math));

        String fromJson = """
                
                {
                  "name": "Math",
                  "lengthWeeks": 10,
                  "createdAt": "Sep 19, 2023, 4:05:13 PM"
                }
                
                """;
        final CourseDto courseDto = gson.fromJson(fromJson, CourseDto.class);
        System.out.println(courseDto);
    }

}

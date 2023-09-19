import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Student;
import dtos.StudentAdditionalInfo;

public class MainFromJson {
    public static void main(String[] args) {
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        String json=" {\"isGraduated\": false,\"averageGrade\": 5.38}";
        final StudentAdditionalInfo studentAdditionalInfoDto =
                gson.fromJson(json, StudentAdditionalInfo.class);

        System.out.println(studentAdditionalInfoDto);

        String studentJson = """    
    {
    "firstName": "first",
    "lastName": null,
    "age": 22,
    "studentAdditionalInfo": {
      "isGraduated": false,
      "averageGrade": 5.38
    },
    "courses": [
      {
        "name": "MAth",
        "lengthWeeks": 20
      },
      {
        "name": "Biology",
        "lengthWeeks": 30
      }
    ]
  }
                  """;
        Student student = gson.fromJson(studentJson,Student.class);
        System.out.println(student);
    }
}

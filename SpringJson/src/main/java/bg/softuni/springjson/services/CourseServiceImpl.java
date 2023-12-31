package bg.softuni.springjson.services;

import bg.softuni.springjson.dtos.CourseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class CourseServiceImpl {

    private final Gson gson;

    public CourseServiceImpl(@Qualifier("withExpose") Gson gson){
        this.gson =gson;
    }
    public void createCourse(String createJson){
         CourseDTO courseDTO = this.gson.fromJson(createJson, CourseDTO.class);

        System.out.println("Created -> " + courseDTO);
    }
}

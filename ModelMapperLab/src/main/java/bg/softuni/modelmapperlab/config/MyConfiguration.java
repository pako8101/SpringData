package bg.softuni.modelmapperlab.config;

import bg.softuni.modelmapperlab.DTO.CreateEmployeeDTO;
import bg.softuni.modelmapperlab.model.Employee;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MyConfiguration {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        PropertyMap<CreateEmployeeDTO, Employee> employeeMap = new PropertyMap<CreateEmployeeDTO, Employee>() {
//            @Override
//            protected void configure() {
//                String birthDayString = source.getBirthday() ;
//             if (birthDayString!= null) {
//                 LocalDate birthday = LocalDate.parse(birthDayString);
//                 map().setBirthday(birthday);
//             }
//            }
//        };
//
//        modelMapper.addMappings(employeeMap);
        Converter<String,LocalDate> toLocalDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                LocalDate result = null;
                if (source!=null){
                    result = LocalDate.parse(source);
                }
                return result;
            }
        };

        modelMapper.addConverter(toLocalDate);
        return modelMapper;

    }
}

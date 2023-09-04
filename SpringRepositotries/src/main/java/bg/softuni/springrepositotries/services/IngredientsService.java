package bg.softuni.springrepositotries.services;

import bg.softuni.springrepositotries.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IngredientsService {

    List<Ingredient> findByName(String name);

    List<Ingredient> findByNameWithinOrderByPrice(List<String> names);


    void increasePrice();


    void deleteByName(String name);
}

package bg.softuni.springrepositotries.services;

import bg.softuni.springrepositotries.entities.Ingredient;
import bg.softuni.springrepositotries.repositories.IngredientsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IngredientsServiceImpl implements IngredientsService {
private final IngredientsRepository ingredientsRepository;

    public IngredientsServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    @Transactional
    public List<Ingredient> findByName(String name) {
        List<Ingredient> ingredients= ingredientsRepository.findByNameStartingWith(name);

        for (Ingredient ingredient : ingredients) {
            System.out.printf("%s -> %d%n",ingredient.getName(),ingredient.getShampoos().size());
        }

        return ingredients;

    }

    @Override
    public List<Ingredient> findByNameWithinOrderByPrice(List<String> names) {
        return ingredientsRepository.findByNameInOrderByPrice(names);
    }

    @Override
    public void increasePrice() {
        ingredientsRepository.increasePriceBy10Percent();
    }

    @Override
    public void deleteByName(String name) {
        ingredientsRepository.deleteIngredientsByName(name);
    }
}

package bg.softuni.springrepositotries.repositories;

import bg.softuni.springrepositotries.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient,Long> {


    List<Ingredient> findByNameStartingWith(String name);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);

    @Modifying
    @Transactional
    @Query("update Ingredient as i set i.name = concat(i.name,'updated') ")
    void increasePriceBy10Percent();



//    @Query()
    @Transactional
    void deleteIngredientsByName(String name);

}

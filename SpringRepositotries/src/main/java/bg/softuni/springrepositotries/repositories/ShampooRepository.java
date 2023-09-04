package bg.softuni.springrepositotries.repositories;

import bg.softuni.springrepositotries.entities.Shampoo;
import bg.softuni.springrepositotries.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo,Long> {
    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);


    int countByPriceLessThan(BigDecimal price);

    @Query("select s from Shampoo  as s join  s.ingredients as i where i.name in:names")
    List<Shampoo> findByIngredientsNameIn(@Param("names") List<String> ingredientNames);
}

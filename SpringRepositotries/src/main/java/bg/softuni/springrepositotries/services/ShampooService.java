package bg.softuni.springrepositotries.services;

import bg.softuni.springrepositotries.entities.Shampoo;
import bg.softuni.springrepositotries.entities.Size;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ShampooService {


    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabelId(Size size, long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int findCheaperThanCount(BigDecimal price);

    List<Shampoo> findAllWithIngredients(List<String> ingredientNames);
}

package bg.softuni.springrepositotries;

import bg.softuni.springrepositotries.entities.Ingredient;
import bg.softuni.springrepositotries.entities.Shampoo;
import bg.softuni.springrepositotries.services.IngredientsService;
import bg.softuni.springrepositotries.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientsService ingredientsService;

    public Runner(ShampooService shampooService, IngredientsService ingredientsService) {
        this.shampooService = shampooService;
        this.ingredientsService = ingredientsService;
    }

    @Override
    public void run(String... args) throws Exception {
//
//     List<Shampoo> byBrand = shampooService.findByBrand("Swiss Green Apple & Nettle");
//
//        List<Shampoo> byBrandAndSize = shampooService.findByBrandAndSize("Swiss Green Apple & Nettle", Size.SMALL);
//
//     byBrandAndSize.forEach(System.out::println);

        //List<Shampoo> bySize = shampooService.findBySizeOrderById(Size.MEDIUM);
        // bySize.sort((l,r) -> l.getId().compareTo(r.getId()));
        //bySize.sort(Comparator.comparing(BaseEntity::getId));


        // List<Shampoo> result =shampooService.findBySizeOrLabelId(Size.MEDIUM, 10L);

//        List<Shampoo> result =shampooService.findByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(5));

    //    List<Ingredient> result = ingredientsService.findByName("M");


        //  List<Ingredient> result =  ingredientsService.findByNameWithinOrderByPrice(List.of( "Lavender" ,
//                "Herbs",
//                "Apple"));


//int count = shampooService.findCheaperThanCount(BigDecimal.valueOf(8.50));
//
//        System.out.println(count);

        //  result.forEach(System.out::println);

//List<Shampoo> result = shampooService.findAllWithIngredients(List.of("Berry",
//        "Mineral-Collagen"));
//
        //result.forEach(System.out::println);

        //  ingredientsService.increasePrice();

        //  ingredientsService.deleteByName("Apple");

    }
}

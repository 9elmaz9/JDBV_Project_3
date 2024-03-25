package jdbc;

import jdbc.models.Beer;
import jdbc.models.Category;
import jdbc.repositories.BeerRepository;
import jdbc.repositories.CategoryRepository;

import java.util.List;

public class DatabaseApp {
    public static void main(String[] args) {
//1
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.read();



        //2
        BeerRepository beerRepository = new BeerRepository();

        // Test create
        beerRepository.create("Test Beer", 1, 1, 2.99f, 100, 5.0f);

        // Test readAll
        List<Beer> beers = beerRepository.readAll();
        System.out.println("All beers:");
        for (Beer beer : beers) {
            System.out.println(beer);
        }

        // Test update (assuming there's a beer with id = 1)
        beerRepository.update(1, "Updated Beer", 2, 2, 3.99f, 50, 6.0f);

        // Test delete (assuming there's a beer with id = 1)
        beerRepository.delete(1);

    }
}

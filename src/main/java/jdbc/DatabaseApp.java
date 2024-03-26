//ckage jdbc;

//port jdbc.models.Beer;
//port jdbc.models.Brewer;
//port jdbc.models.Category;
//port jdbc.repositories.BeerRepository;
//port jdbc.repositories.BreweryRepository;
//port jdbc.repositories.CategoryRepository;
//port java.util.List;

//blic class DatabaseApp {
//  public static void main(String[] args) {
//      CategoryRepository categoryRepository = new CategoryRepository();
//      BeerRepository beerRepository = new BeerRepository();
//      BreweryRepository breweryRepository = new BreweryRepository();

//      System.out.println("Reading all categories:");
//      List<Category> categories = categoryRepository.read();
//      for (Category category : categories) {
//          System.out.println("Category ID: " + category.getId() + ", Name: " + category.getClass());
//      }

//      Category newCategory = new Category(19, "New Category");
//      categoryRepository.create(newCategory);
//      System.out.println("New category created!");

//      Category categoryToUpdate = new Category(8, "Updated Category");
//      categoryRepository.update(categoryToUpdate);
//      System.out.println("Category updated!");



//      System.out.println("Reading all beers:");
//      List<Beer> beers = beerRepository.readAllBeers();
//      for (Beer beer : beers) {
//          System.out.println(beer);
//      }

//      System.out.println("Reading all brewers:");
//      List<Brewer> brewery = breweryRepository.read();
//      for (Brewer brewer : brewery) {
//          System.out.println(brewer);
//      }

//      Beer newBeer = new Beer(25, "New Beer", 1, 55, 10.99f, 100, 5.0f);
//      beerRepository.createBeer(newBeer);
//      System.out.println("New beer created!");

//      Beer beerToUpdate = new Beer(1, "Updated Beer", 2, 75, 12.99f, 150, 6.0f);
//      beerRepository.updateBeer(beerToUpdate);
//      System.out.println("Beer updated!");

//      int beerIdToDelete = 18;
//      beerRepository.deleteBeer(beerIdToDelete);
//      System.out.println("Beer deleted!");

//      int categoryIdToDelete = 3;
//      categoryRepository.delete(categoryIdToDelete);
//      System.out.println("Category deleted!");



package jdbc;

import jdbc.models.Beer;
import jdbc.models.Brewer;
import jdbc.models.CategoryId;
import jdbc.repositories.BeerRepository;
import jdbc.repositories.BreweryRepository;
import jdbc.repositories.CategoryRepository;
import java.util.List;

public class DatabaseApp {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BeerRepository beerRepository = new BeerRepository();
        BreweryRepository breweryRepository = new BreweryRepository();

        System.out.println("Reading all categories:");
        List<CategoryId> categories = categoryRepository.read();
        for (CategoryId category : categories) {
            System.out.println("Category ID: " + category.getId() + ", Name: " + category.getTitle());
        }

        CategoryId newCategory = new CategoryId(19, "New Category");
        categoryRepository.create(newCategory);
        System.out.println("New category created!");

        CategoryId categoryToUpdate = new CategoryId(8, "Updated Category");
        categoryRepository.update(categoryToUpdate);
        System.out.println("Category updated!");

        System.out.println("Reading all beers:");
        List<Beer> beers = beerRepository.readAllBeers();
        for (Beer beer : beers) {
            System.out.println(beer);
        }

        System.out.println("Reading all brewers:");
        List<Brewer> brewers = breweryRepository.read();
        for (Brewer brewer : brewers) {
            System.out.println(brewer);
        }

        Beer newBeer = new Beer(25, "New Beer", 1, 55, 10.99f, 100, 5.0f);
        beerRepository.createBeer(newBeer);
        System.out.println("New beer created!");

        Beer beerToUpdate = new Beer(1, "Updated Beer", 2, 75, 12.99f, 150, 6.0f);
        beerRepository.updateBeer(beerToUpdate);
        System.out.println("Beer updated!");

        int beerIdToDelete = 18;
        beerRepository.deleteBeer(beerIdToDelete);
        System.out.println("Beer deleted!");

        int categoryIdToDelete = 3;
        categoryRepository.delete(categoryIdToDelete);
        System.out.println("Category deleted!");
    }
}

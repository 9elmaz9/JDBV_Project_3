package jdbc;
import jdbc.models.Beer;
import jdbc.models.Brewer;
import jdbc.models.Category;
import jdbc.repositories.BeerRepository;
import jdbc.repositories.BreweryRepository;
import jdbc.repositories.CategoryRepository;
import java.util.List;
public class DatabaseApp {
    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        BeerRepository beerRepository = new BeerRepository();
        BreweryRepository breweryRepository = new BreweryRepository();
// Чтение всех категорий
// Read all categories
        System.out.println("Reading all categories:");
        List<Category> categories = categoryRepository.read();
        for (Category category : categories) {
            System.out.println("Category ID: " + category.getId() + ", Name: " + category.getTitle());
        }
// Создание новой категории
// Create a new category
        Category newCategory = new Category(0, "New Category");
        categoryRepository.create(newCategory);
        System.out.println("New category created!");
// Обновление категории
// Update category
        Category categoryToUpdate = new Category(1, "Updated Category");
        categoryRepository.update(categoryToUpdate);
        System.out.println("Category updated!");
// Удаление категории
// Delete a category
        int categoryIdToDelete = 2; // Suppose we delete a category with ID = 2// Предположим, что удаляем категорию с ID = 2
        categoryRepository.delete(categoryIdToDelete);
        System.out.println("Category deleted!");
// Чтение всех пив из базы данных и вывод на консоль
// Read all beers from the database and output to the console
        System.out.println("Reading all beers:");
        List<Beer> beers = beerRepository.read();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
// Чтение всех производителей из базы данных и вывод на консоль
// Read all manufacturers from the database and output to the console
        System.out.println("Reading all brewers:");
        List<Brewer> brewery = breweryRepository.read();
        for (Brewer brewer : brewery) {
            System.out.println(brewer);
        }
// Создание нового пива и вставка его в базу данных
// Create a new beer and insert it into the database
        Beer newBeer = new Beer(0, "New Beer", 1, 1, 10.99f, 100, 5.0f, 1);
        beerRepository.create(newBeer);
        System.out.println("New beer created!");
// Обновление информации о пиве
// Update beer information
        Beer beerToUpdate = new Beer(1, "Updated Beer", 2, 2, 12.99f, 150, 6.0f, 2);
        beerRepository.update(beerToUpdate);
        System.out.println("Beer updated!");
// Удаление пива из базы данных
// Removing beer from the database
        int beerIdToDelete = 18; // Suppose we remove beer with ID = 3// Предположим, что удаляем пиво с ID = 3
        beerRepository.delete(beerIdToDelete);
        System.out.println("Beer deleted!");
    }
}
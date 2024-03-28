package jdbc;

import jdbc.repositories.BeerRepository;
import jdbc.repositories.BreweryRepository;
import jdbc.repositories.CategoriesRepository;

public class DatabaseApp {
    public static void main(String[] args) {



     // //BeerRepository operations
     // System.out.println("Fetching beer data");
     // BeerRepository beerRepository = new BeerRepository();
     // beerRepository.read();
     // System.out.println("Adding new beers");
     // beerRepository.makeConnection();
     // System.out.println("Updating beer information");
     // beerRepository.update();
     // System.out.println("Deleting beers");
     // beerRepository.delete();
     // System.out.println("~     ~     ~     ~     ~     ~     ~");


    // // BreweryRepository operations
    // System.out.println("Browsing breweries!");
    // BreweryRepository brewerRepository = new BreweryRepository();
    // System.out.println("Listing breweries:");
    // brewerRepository.read();
    // System.out.println("Adding a new brewery...");
    // brewerRepository.makeConnection();
    // System.out.println("Updating brewery details...");
    // brewerRepository.update();
    // System.out.println("Deleting a brewery...");
    // brewerRepository.delete();
    // System.out.println("~     ~     ~     ~     ~     ~     ~");



        // CategoriesRepository operations
        System.out.println("Fetching categories!");
        CategoriesRepository categoryRepository = new CategoriesRepository();
        System.out.println("Displaying available categories:");
        categoryRepository.read();
        System.out.println("Adding a new category...");
        categoryRepository.makeConnection();
        System.out.println("Updating category information...");
        categoryRepository.update();
        System.out.println("Deleting a category...");
        categoryRepository.delete();
        System.out.println("~     ~     ~     ~     ~     ~     ~-");

    }
}





package jdbc;

import jdbc.repositories.CategoriesRepository;

public class DatabaseApp {
    public static void main(String[] args) {


            System.out.println("Read List categories");
            CategoriesRepository categoryRepository = new CategoriesRepository();
            categoryRepository.read();
            System.out.println();
            System.out.println("create into category");
            categoryRepository.create();
            System.out.println("update category");
            categoryRepository.update();
            System.out.println("delete category");
            categoryRepository.delete();
//------------------------------------------------------------------
            // System.out.println("Read List Brewers");
            //BrewerRepository brewerRepository = new BrewerRepository();
            //brewerRepository.read();
            //System.out.println("create brewers");
            //brewerRepository.create();
            //System.out.println("update brewer");
            //brewerRepository.update();
            //System.out.println("delete brewers");
            //brewerRepository.delete();
//----------------------------------------------------------------------------
            // System.out.println("Read List Beers");
            //BeerRepository beerRepository = new BeerRepository();
            //beerRepository.read();
            //System.out.println("create beers");
            //beerRepository.create();
            //System.out.println("update beers");
            //beerRepository.update();
            //System.out.println("delete beers");
            //beerRepository.delete();

        }


    }




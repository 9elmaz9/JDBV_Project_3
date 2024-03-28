package jdbc.repositories;

import jdbc.models.Categories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {

    // CRUD operations:
    // Create (Insert), Read (Select), Update, and Delete

    public void create(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection is made with category table");
            Statement statement = connection.createStatement();
            String query = "insert into categories value (66,'555Category')";
            statement.execute(query);
            System.out.println("new row added to categories table");
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public List<Categories> read(){

        // Polymorphism .
        List<Categories> results = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("CONNECTION TO DB IS MADE");
            Statement statement = connection.createStatement();
            // if this query returns multiple results, then read() method must return
            // either an array or a list.
            String query  = "SELECT  Id, Category FROM categories";
            // CTRL + ALT + V  then ENTER
            ResultSet resultSet = statement.executeQuery(query);
            // STEP 03: make binding between Java Objects and Tables

            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt("Id"));
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL EXCEPTION: " + sqlException.getMessage());
        }

        return results;

    }

    public void update(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection is made with category table");
            Statement statement = connection.createStatement();
            String query = "update categories set category='new elmaz categories' where id =66";
            statement.execute(query);
            System.out.println("categories table update");
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    public void delete(){
        try {
            Connection connection =DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("connection is made with category table");
            Statement statement = connection.createStatement();
            String query ="delete from category where id=65";
            statement.execute(query);
            System.out.println("Data Deleted from categories table");
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

}


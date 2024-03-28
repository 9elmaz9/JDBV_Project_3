package jdbc.repositories;
import jdbc.models.Categories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {

    private final String url = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private final String username = "elmaz";
    private final String password = "54321";

    public void makeConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection is made with Categories Table");

            String query = "INSERT INTO categories VALUE (66, '555Categories')";
            statement.execute(query);

            System.out.println("Recent addition to Categories table");
            System.out.println("!"+query);

        } catch (SQLException sqlException) {
            if (sqlException instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("Cannot delete or update a parent row: a foreign key constraint fails.");
                System.out.println(sqlException.getMessage());
            } else {
                System.out.println(sqlException.getMessage());
            }
        }

      // } catch (SQLException sqlException) {
      //     System.out.println(sqlException.getMessage());


    }

    public List<Categories> read() {
        List<Categories> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection to database is made");

            String query = "SELECT Id, Category FROM categories";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String category = resultSet.getString("Category");
                    Categories categories = new Categories(id, category);
                    results.add(categories);

                }
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
        return results;
    }

    public void update() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection is made with Categories Table");

            String query = "UPDATE categories SET Category = 'NEW_A.C.0' WHERE id = 4";
            statement.execute(query);

            System.out.println("Categories Table modified");
            System.out.println("!"+query);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void delete() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection is made with Categories Table");

            String query = "DELETE FROM categories WHERE id = 5";
            statement.execute(query);

            System.out.println("Data erased from Categories Table");
            System.out.println("!"+query);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}

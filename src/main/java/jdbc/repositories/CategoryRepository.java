 package jdbc.repositories;
import jdbc.models.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategoryRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";

// CRUD operation

// Create (Insert), Read(Select), Update, and Delete

    public List<Category> read() {

        List<Category> results = new ArrayList<>();

        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("CONNECTION TO DB IS ESTABLISHED");

            String query = "SELECT * FROM Category";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                String categoryName = resultSet.getString("category");

                Category category = new Category(id, categoryName);

                results.add(category);

            }

        } catch (SQLException sqlException) {

            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
        return results;
    }
    public void create(Category category) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        try {

// Устанавливаем соединение с базой данных

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("CONNECTION TO DB IS ESTABLISHED");

// Создаем SQL запрос для вставки новой записи в таблицу Category

            String query = "INSERT INTO Category (category) VALUES (?)";

// Подготавливаем SQL выражение

            preparedStatement = connection.prepareStatement(query);

// Устанавливаем значение параметра

            preparedStatement.setString(1, category.getTitle());

// Выполняем SQL запрос для вставки записи
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {

                System.out.println("New category inserted successfully!");

            } else {

                System.out.println("Failed to insert new category!");

            }

        } catch (SQLException sqlException) {

            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
    }
    public void update(Category category) {

        Connection connection = null;

        PreparedStatement statement = null;
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("CONNECTION TO DB IS ESTABLISHED");

            String query = "UPDATE Category SET category=? WHERE id=?";

            statement = connection.prepareStatement(query);

// Устанавливаем значения параметров запроса

            statement.setString(1, category.getTitle());

            statement.setInt(2, category.getId());

// Выполняем запрос на обновление данных
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

                System.out.println("Category with ID " + category.getId() + " was updated successfully.");

            } else {

                System.out.println("No category found with ID " + category.getId() + ". No changes were made.");

            }

        } catch (SQLException sqlException) {

            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
    }
    public void delete(int categoryId) {

        Connection connection = null;

        PreparedStatement statement = null;
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("CONNECTION TO DB IS ESTABLISHED");

            String query = "DELETE FROM Category WHERE id=?";

            statement = connection.prepareStatement(query);

            statement.setInt(1, categoryId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {

                System.out.println("Category with ID " + categoryId + " deleted successfully.");

            } else {

                System.out.println("No category found with ID " + categoryId + ". No changes were made.");

            }

        } catch (SQLException sqlException) {

            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }
    }
}

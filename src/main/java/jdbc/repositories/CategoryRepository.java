package jdbc.repositories;

import jdbc.models.CategoryId;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";

    public List<CategoryId> read() {
        List<CategoryId> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Category ORDER BY id ASC")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("category");
                results.add(new CategoryId(id, categoryName));
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
        return results;
    }

    public void create(CategoryId category) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Category (category) VALUES (?)")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            preparedStatement.setString(1, category.getTitle());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New category inserted successfully!");
            } else {
                System.out.println("Failed to insert new category!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }

    public void update(CategoryId category) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Category SET category=? WHERE id=?")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            statement.setString(1, category.getTitle());
            statement.setInt(2, category.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Category with ID " + category.getId() + " was updated successfully.");
            } else {
                System.out.println("No category found with ID " + category.getId() + ". No changes were made.");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }

    public void delete(int categoryId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Category WHERE id=?")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            statement.setInt(1, categoryId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Category with ID " + categoryId + " deleted successfully.");
            } else {
                System.out.println("No category found with ID " + categoryId + ". No changes were made.");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }
}

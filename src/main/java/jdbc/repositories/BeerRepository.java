package jdbc.repositories;

import jdbc.models.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private boolean isCategoryExists(int categoryId) {
        try (Connection connection = getConnection()) {
            String query = "SELECT Id FROM categories WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return false;
        }
    }

    private void insertCategory(int categoryId, String categoryName) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO categories (Id, Category) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setString(2, categoryName);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inserted category with ID: " + categoryId);
            } else {
                System.out.println("Failed to insert category with ID: " + categoryId);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public void createBeer(Beer beer) {
        if (!isCategoryExists(beer.getCategoryId())) {
            insertCategory(beer.getCategoryId(), "New Category");
        }
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO beers (Name, BrewerId, CategoryId, Price, Stock, Alcohol) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, beer.getName());
            preparedStatement.setInt(2, beer.getBrewerId());
            preparedStatement.setInt(3, beer.getCategoryId());
            preparedStatement.setFloat(4, beer.getPrice());
            preparedStatement.setInt(5, beer.getStock());
            preparedStatement.setFloat(6, beer.getAlcohol());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inserted beer: " + beer.getName());
            } else {
                System.out.println("Failed to insert beer: " + beer.getName());
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public List<Beer> readAllBeers() {
        List<Beer> beers = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT Id, Name, BrewerId, CategoryId, Price, Stock, Alcohol FROM beers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Beer beer = new Beer(
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        resultSet.getInt("BrewerId"),
                        resultSet.getInt("CategoryId"),
                        resultSet.getFloat("Price"),
                        resultSet.getInt("Stock"),
                        resultSet.getFloat("Alcohol")
                );
                beers.add(beer);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return beers;
    }

    public void updateBeer(Beer beer) {
        if (!isCategoryExists(beer.getCategoryId())) {
            insertCategory(beer.getCategoryId(), "New Category");
        }
        try (Connection connection = getConnection()) {
            String query = "UPDATE beers SET Name = ?, BrewerId = ?, CategoryId = ?, Price = ?, Stock = ?, Alcohol = ? WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, beer.getName());
            preparedStatement.setInt(2, beer.getBrewerId());
            preparedStatement.setInt(3, beer.getCategoryId());
            preparedStatement.setFloat(4, beer.getPrice());
            preparedStatement.setInt(5, beer.getStock());
            preparedStatement.setFloat(6, beer.getAlcohol());
            preparedStatement.setInt(7, beer.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated beer with ID: " + beer.getId());
            } else {
                System.out.println("Failed to update beer with ID: " + beer.getId());
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteBeer(int beerId) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM beers WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, beerId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted beer with ID: " + beerId);
            } else {
                System.out.println("Failed to delete beer with ID: " + beerId);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}

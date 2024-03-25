package jdbc.repositories;


import jdbc.models.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerRepository {

    // Connection URL and credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";

    // Method to establish connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    // Method to create a new beer in the database
    public void create(String name, int brewerId, int categoryId, float price, int stock, float alcohol) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO beers (Name, BrewerId, CategoryId, Price, Stock, Alcohol) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, brewerId);
            preparedStatement.setInt(3, categoryId);
            preparedStatement.setFloat(4, price);
            preparedStatement.setInt(5, stock);
            preparedStatement.setFloat(6, alcohol);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Insert successful: " + rowsAffected + " row(s) inserted.");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    // Method to retrieve all beers from the database
    public List<Beer> readAll() {
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

    // Method to update an existing beer in the database
    public void update(int id, String name, int brewerId, int categoryId, float price, int stock, float alcohol) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE beers SET Name = ?, BrewerId = ?, CategoryId = ?, Price = ?, Stock = ?, Alcohol = ? WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, brewerId);
            preparedStatement.setInt(3, categoryId);
            preparedStatement.setFloat(4, price);
            preparedStatement.setInt(5, stock);
            preparedStatement.setFloat(6, alcohol);
            preparedStatement.setInt(7, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Update successful: " + rowsAffected + " row(s) updated.");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    // Method to delete a beer from the database
    public void delete(int id) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM beers WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Delete successful: " + rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

}

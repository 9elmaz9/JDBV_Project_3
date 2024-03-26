package jdbc.repositories;


import jdbc.models.Brewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreweryRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";

    public List<Brewer> read() {
        List<Brewer> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            String query = "SELECT * FROM Brewer";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String zipcode = resultSet.getString("zipcode");
                    String city = resultSet.getString("city");
                    double turnover = resultSet.getDouble("turnover");
                    Brewer brewer = new Brewer(id, name, address, zipcode, city, (int) turnover);
                    results.add(brewer);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
        return results;
    }

    public void create(Brewer brewer) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Brewer (name, address, zipcode, city, turnover) VALUES (?, ?, ?, ?, ?)")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            preparedStatement.setString(1, brewer.getName());
            preparedStatement.setString(2, brewer.getAddress());
            preparedStatement.setString(3, brewer.getZipCode());
            preparedStatement.setString(4, brewer.getCity());
            preparedStatement.setDouble(5, brewer.getTurnover());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New brewer inserted successfully!");
            } else {
                System.out.println("Failed to insert new brewer!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }

    public void update(Brewer brewer) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Brewer SET name=?, address=?, zipcode=?, city=?, turnover=? WHERE id=?")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            statement.setString(1, brewer.getName());
            statement.setString(2, brewer.getAddress());
            statement.setString(3, brewer.getZipCode());
            statement.setString(4, brewer.getCity());
            statement.setDouble(5, brewer.getTurnover());
            statement.setInt(6, brewer.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Brewer with ID " + brewer.getId() + " was updated successfully.");
            } else {
                System.out.println("No brewer found with ID " + brewer.getId() + ". No changes were made.");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }

    public void delete(int brewerId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Brewer WHERE id=?")) {
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            statement.setInt(1, brewerId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Brewer with ID " + brewerId + " deleted successfully.");
            } else {
                System.out.println("No brewer found with ID " + brewerId + ". No changes were made.");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }
}

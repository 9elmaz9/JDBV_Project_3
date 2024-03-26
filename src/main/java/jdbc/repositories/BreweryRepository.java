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
            Connection connection;
            connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("CONNECTION TO DB IS ESTABLISHED");
                String query = "SELECT * FROM Brewer";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String zipcode = resultSet.getString("zipcode");
                    String city = resultSet.getString("city");
                    double turnover = resultSet.getDouble("turnover");
// Добавляем новый экземпляр класса Brewer в список результатов
// Add a new instance of the Brewer class to the list of results
                    Brewer brewer = new Brewer(id, name, address, zipcode, city, (int) turnover);
                    results.add(brewer);
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
        public void create(Brewer brewer) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
// Устанавливаем соединение с базой данных
// Establish a connection to the database
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("CONNECTION TO DB IS ESTABLISHED");
// Создаем SQL запрос для вставки новой записи в таблицу Brewer
// Create an SQL query to insert a new record into the Brewer table
                String query = "INSERT INTO Brewer (name, address, zipcode, city, turnover) VALUES (?, ?, ?, ?, ?)";
// Подготавливаем SQL выражение
// Prepare the SQL expression
                preparedStatement = connection.prepareStatement(query);
// Устанавливаем значения параметров
// Set parameter values
                preparedStatement.setString(1, brewer.getName());
                preparedStatement.setString(2, brewer.getAddress());
                preparedStatement.setString(3, brewer.getZipCode());
                preparedStatement.setString(4, brewer.getCity());
                preparedStatement.setDouble(5, brewer.getTurnover());
// Выполняем SQL запрос для вставки записи
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("New brewer inserted successfully!");
                } else {
                    System.out.println("Failed to insert new brewer!");
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
        public void update(Brewer brewer) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("CONNECTION TO DB IS ESTABLISHED");
                String query = "UPDATE Brewer SET name=?, address=?, zipcode=?, city=?, turnover=? WHERE id=?";
                statement = connection.prepareStatement(query);
// Устанавливаем значения параметров запроса
                statement.setString(1, brewer.getName());
                statement.setString(2, brewer.getAddress());
                statement.setString(3, brewer.getZipCode());
                statement.setString(4, brewer.getCity());
                statement.setDouble(5, brewer.getTurnover());
                statement.setInt(6, brewer.getId());
// Выполняем запрос на обновление данных
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Brewer with ID " + brewer.getId() + " was updated successfully.");
                } else {
                    System.out.println("No brewer found with ID " + brewer.getId() + ". No changes were made.");
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
        public void delete(int brewerId) {
            Connection connection = null;
            PreparedStatement statement = null;
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("CONNECTION TO DB IS ESTABLISHED");
                String query = "DELETE FROM Brewer WHERE id=?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, brewerId);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Brewer with ID " + brewerId + " deleted successfully.");
                } else {
                    System.out.println("No brewer found with ID " + brewerId + ". No changes were made.");
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

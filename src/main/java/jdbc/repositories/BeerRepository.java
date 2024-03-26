package jdbc.repositories;
import jdbc.models.Beer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BeerRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private static final String USERNAME = "elmaz";
    private static final String PASSWORD = "54321";
    public List<Beer> read() {
        List<Beer> results = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            String query = "SELECT * FROM Beers";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int brewerId = resultSet.getInt("brewer_id");
                int categoryId = resultSet.getInt("category_id");
                float price = resultSet.getFloat("price");
                int stock = resultSet.getInt("stock");
                float alcohol = resultSet.getFloat("alcohol");
                int version = resultSet.getInt("version"); // Добавить версию
// Добавляем новый экземпляр класса Beer в список результатов
                Beer beer = new Beer(id, name, brewerId, categoryId, price, stock, alcohol, version);
                results.add(beer);
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
    public void create(Beer beer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
// Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
// Создаем SQL запрос для вставки новой записи в таблицу Beers
            String query = "INSERT INTO Beers (name, brewer_id, category_id, price, stock, alcohol, version) VALUES (?, ?, ?, ?, ?, ?, ?)";
// Подготавливаем SQL выражение
            preparedStatement = connection.prepareStatement(query);
// Устанавливаем значения параметров
            preparedStatement.setString(1, beer.getName());
            preparedStatement.setInt(2, beer.getBrewerId());
            preparedStatement.setInt(3, beer.getCategoryId());
            preparedStatement.setFloat(4, beer.getPrice());
            preparedStatement.setInt(5, beer.getStock());
            preparedStatement.setFloat(6, beer.getAlcohol());
            preparedStatement.setInt(7, beer.getVersion());
// Выполняем SQL запрос для вставки записи
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New beer inserted successfully!");
            } else {
                System.out.println("Failed to insert new beer!");
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

    public void update(Beer beer) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            String query = "UPDATE Beers SET name=?, brewer_id=?, category_id=?, price=?, stock=?, alcohol=?, version=? WHERE id=?";
            statement = connection.prepareStatement(query);
// Устанавливаем значения параметров запроса
            statement.setString(1, beer.getName());
            statement.setInt(2, beer.getBrewerId());
            statement.setInt(3, beer.getCategoryId());
            statement.setFloat(4, beer.getPrice());
            statement.setInt(5, beer.getStock());
            statement.setFloat(6, beer.getAlcohol());
            statement.setInt(7, beer.getVersion());
            statement.setInt(8, beer.getId());
// Выполняем запрос на обновление данных
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Beer with ID " + beer.getId() + " was updated successfully.");
            } else {
                System.out.println("No beer found with ID " + beer.getId() + ". No changes were made.");
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

    public void delete(int beerId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("CONNECTION TO DB IS ESTABLISHED");
            String query = "DELETE FROM Beers WHERE id=?";
            statement = connection.prepareStatement(query);
// Устанавливаем значение параметра запроса
            statement.setInt(1, beerId);
// Выполняем запрос на удаление данных
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Beer with ID " + beerId + " was deleted successfully.");
            } else {
                System.out.println("No beer found with ID " + beerId + ". No changes were made.");
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
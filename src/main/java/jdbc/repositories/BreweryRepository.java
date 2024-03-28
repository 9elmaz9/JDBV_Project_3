package jdbc.repositories;

import jdbc.models.Brewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreweryRepository {

    private final String url = "jdbc:mysql://localhost:3306/thebelgianbrewerydb";
    private final String username = "elmaz";
    private final String password = "54321";

    public void makeConnection() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection is made with Brewers Table");

            String query = "INSERT INTO brewers(id,Name, Address, Zipcode, City, Turnover) " +
                    "VALUES (2000,'HUGARDEN', 'Katestraatg', 9700, 'Oudenaarde', 30000)";
            statement.execute(query);

            System.out.println("Recent addition to Brewers Table");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public List<Brewer> read() {
        List<Brewer> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection made with Brewers Table");

            String query = "SELECT * FROM brewers";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                System.out.println("Fresh row added to beers table:Id~~Name~~Address~~Zipcode~~City~~Turnover");
                while (resultSet.next()) {
                    System.out.println(resultSet.getRow() + " | " +
                            resultSet.getInt("id") + " | " +
                            resultSet.getString("name") + " | " +
                            resultSet.getString("address") + " | " +
                            resultSet.getInt("zipcode") + " | " +
                            resultSet.getString("city") + " | " +
                            resultSet.getInt("turnover"));
                }
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return result;
    }

    public void update() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection made with Brewers Table");

            String query = "UPDATE brewers SET Name ='HUGARDEN' WHERE id = 607";
            statement.execute(query);

            System.out.println("Brewers Table modified");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void delete() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connection made with Brewers Table");

            String query = "DELETE FROM brewers WHERE id= 607";
            statement.execute(query);

            System.out.println("Data erased from Brewers Table");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}



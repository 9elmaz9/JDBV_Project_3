package jdbc.repositories;

import jdbc.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    //CRUD operations:
    //Create (Insert),Read,Update and Delete
    public void create() {
    }

    public List<Category> read() {

        //Polymorphism
        List<Category> results = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );

            System.out.println("CONNECT TO DB IN MADE");
            Statement statement = connection.createStatement();
            //if this query returns multiple results , then read()method return
            //either an array or a list
            String query = "SELECT Id,Category FROM categories";
            //CTRL +ALT + V then ENTER
            ResultSet resultSet = statement.executeQuery(query);
            //STEP 03 : make binding between Java Objects and Tables

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("Id"));
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL EXCEPTION: " + sqlException.getMessage());
        }

        return results;
    }


    public void update(int categoryId, String newCategoryName) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );

            String query = "UPDATE categories SET Category = ? WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newCategoryName);
            preparedStatement.setInt(2, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful: " + rowsAffected + " row(s) updated.");
            } else {
                System.out.println("No rows updated.");
            }

        } catch (SQLException sqlException) {
            System.err.println("SQL EXCEPTION: " + sqlException.getMessage());
        }
    }
        public void delete(int categoryId){
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                        "elmaz",
                        "54321"
                );

                String query = "DELETE FROM categories WHERE Id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, categoryId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Delete successful: " + rowsAffected + " row(s) deleted.");
                } else {
                    System.out.println("No rows deleted.");
                }

            } catch (SQLException sqlException) {
                System.err.println("SQL EXCEPTION: " + sqlException.getMessage());
            }
        }
    }



// public void update(){

// }

// public void delete(){}

// public void main() {

// }



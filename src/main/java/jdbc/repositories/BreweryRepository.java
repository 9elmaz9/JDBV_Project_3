package jdbc.repositories;

import jdbc.models.Brewer;
import jdbc.models.Categories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreweryRepository {

    public void create() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection is made with brewer table");
            Statement statement = connection.createStatement();
            String query = "insert into brewers(Name, Address, Zipcode, City, Turnover) values (JananBrewery,HnrieLibrechlaan,1090,Jette,10000)";
            statement.execute(query);
            System.out.println("new rom added to brewers table");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public List<Brewer> read(){
        List<Brewer> result = new ArrayList<>();
        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection with made with brewers table");
            Statement statement = connection.createStatement();
            String query ="select * from brewers";
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Id|Name|Address|Zipcode|City|Turnover");
            while (resultSet.next()){
                System.out.println(resultSet.getRow() + " | "+
                        resultSet.getInt("id") + " | "+
                        resultSet.getString("name") + " | " +
                        resultSet.getString("address") + " | "+
                        resultSet.getInt("id") + " | " +
                        resultSet.getString("city") + " | " +
                        resultSet.getInt("turnover"));
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return result;
    }
    public void update(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection with made with brewer table");
            Statement statement = connection.createStatement();
            String query = "update brewers set Name ='jananbrewery'where id = 100";
            statement.execute(query);
            System.out.println("brewers table update");
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }
    public void delete(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb",
                    "elmaz",
                    "54321"
            );
            System.out.println("Connection with made with brewer table");
            Statement statement = connection.createStatement();
            String query = "delete from brewers where id= 100";
            statement.execute(query);
            System.out.println("content from brewers table deleted");
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }


}



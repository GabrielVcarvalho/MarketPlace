package dao;

import java.sql.*;

public class ConnectionUtil {
    final private static String USER = "marketplaceuser";
    final private static String PASSWORD = "password";
    final private static String URL = "jdbc:postgresql://localhost:5432/marketplace";

    public static Connection openConnection() {
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro ao abrir conexão");
            return null;
        }
    }

    private static void closeConnection(Connection connection){
        try{
            if(connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void closeStatement(PreparedStatement statement){
        try{
            if(statement != null) statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void closeResultSet(ResultSet resultSet){
        try{
            if(resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void closeAll(Connection connection, PreparedStatement statement){
            closeStatement(statement);
            closeConnection(connection);
    }

    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
    }
}
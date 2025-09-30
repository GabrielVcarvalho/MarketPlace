package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {
    final private static String USER = "marketplaceuser";
    final private static String PASSWORD = "password";
    final private static String URL = "jdbc:postgresql://localhost:5432/marketplace";

    public static Connection openConnetion() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if(connection != null) connection.close();
    }

    public static void closeStatement(PreparedStatement statement) throws SQLException{
        if(statement != null) statement.close();
    }

    public static void closeAll(Connection connection, PreparedStatement statement) throws SQLException{
        closeConnection(connection);
        closeStatement(statement);
    }
}

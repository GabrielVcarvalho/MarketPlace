package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeslikeDAO {
    public void createDeslike(int idAnuncio, int idUsuario){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO deslikes (anuncio, usuario) VALUES (?, ?)");
            preparedStatement.setInt(1, idAnuncio);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement);
        }
    }

    public void deleteDeslike(int idAnuncio, int idUsuario){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM deslikes WHERE anuncio = ?" +
                    " AND usuario = ?");
            preparedStatement.setInt(1, idAnuncio);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement);
        }
    }

    public int readDeslikesOfAd(int idAnuncio){
        int deslikes = 0;
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT id FROM deslikes WHERE anuncio = ?");
            preparedStatement.setInt(1, idAnuncio);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) deslikes++;
            return deslikes;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return 0;
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
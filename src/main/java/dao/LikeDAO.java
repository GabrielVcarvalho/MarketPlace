package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAO {
    public void createLike(int idAnuncio, int idUsuario){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("INSERT INTO likes (anuncio, usuario) VALUES (?, ?)");
            preparedStatement.setInt(1, idAnuncio);
            preparedStatement.setInt(2, idUsuario);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement);
        }
    }

    public void deleteLike(int idAnuncio, int idUsuario){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM likes WHERE anuncio = ?" +
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

    public int readLikesOfAd(int idAnuncio){
        int likes = 0;
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS linhas FROM likes WHERE anuncio = ?");
            preparedStatement.setInt(1, idAnuncio);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                likes = resultSet.getInt("linhas");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }

        return likes;
    }
}
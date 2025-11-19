package dao;

import model.ComentarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComentarioDAO {
    public void createComment(ComentarioEntity comentarioEntity){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO comentarios (autor, anuncio, conteudo) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, comentarioEntity.getIdAuthor());
            preparedStatement.setInt(2, comentarioEntity.getAnuncioId());
            preparedStatement.setString(3, comentarioEntity.getContent());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.err.println("Erro ao abrir conexão para criar o comentário");
        }
        finally {
            ConnectionUtil.closeAll(connection, preparedStatement);
        }
    }

    public ArrayList<ComentarioEntity> readAllCommentsOfOneAd(int anuncioId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ComentarioEntity> commentsOfAd = new ArrayList<>();

        try{
            connection = ConnectionUtil.openConnection();
            preparedStatement = connection.prepareStatement("SELECT autor, anuncio, conteudo FROM comentarios WHERE anuncio = ?");
            preparedStatement.setInt(1, anuncioId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ComentarioEntity commentOutDataBase;
                commentOutDataBase = new ComentarioEntity(
                        resultSet.getInt("autor"),
                        resultSet.getInt("anuncio"),
                        resultSet.getString("conteudo"));

                commentsOfAd.add(commentOutDataBase);
            }

            return commentsOfAd;
        }
        catch (SQLException e){
            System.err.println("Erro ao abrir conexão para ler comentários");
            return null;
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
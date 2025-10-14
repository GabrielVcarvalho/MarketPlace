package DAO;

import MODEL.AnuncioEntity;
import DAO.ConnectionUtil;
import MODEL.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnuncioDAO {
    public void createAnuncio(int idVendedor, String titulo, String descricao){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO anuncios (vendedor, titulo, descricao) values (?, ?, ?)");
            preparedStatement.setInt(1, idVendedor);
            preparedStatement.setString(2, titulo);
            preparedStatement.setString(3, descricao);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement);
        }
    }

    public void deleteAnuncio(int idVendedor, String titulo){
        
    }

    public AnuncioEntity readAnuncioById(int id){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT id, vendedor, titulo, descricao, likes, deslikes FROM anuncios WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) return new AnuncioEntity(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6));
            else return null;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }
    }

    public AnuncioEntity readAnuncioByName(String nome){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT id, vendedor, titulo, descricao, likes, deslikes FROM anuncios WHERE nome = ?");
            preparedStatement.setString(1, nome);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) return new AnuncioEntity(resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6));
            else return null;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }
    }

    public ArrayList<AnuncioEntity> readAllFromAnuncios(){
        Connection connection = ConnectionUtil.openConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AnuncioEntity> anuncioEntities = new ArrayList<>();

        try{
            preparedStatement = connection.prepareStatement("SELECT id, vendedor, titulo, descricao, likes, deslikes FROM anuncios");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                AnuncioEntity anuncio = new AnuncioEntity(resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6));
                anuncioEntities.add(anuncio);
            }

            return anuncioEntities;

        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }finally {
            ConnectionUtil.closeAll(connection, preparedStatement, resultSet);
        }
    }

    public void updateLikes(AnuncioEntity anuncio){

    }

    public void updateDeslikes(AnuncioEntity anuncio){

    }
}

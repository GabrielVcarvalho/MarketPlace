package REPOSITORY;

import MODEL.UsuarioEntity;
import DAO.QueryTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {
    public static void salvarUsuario(UsuarioEntity usuario){
        QueryTemplate.Usuario.createUsuario(usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(), usuario.getRole());
    }

    public static UsuarioEntity buscarUsuarioPorId(int id){
        try{
            ResultSet resultSet = QueryTemplate.Usuario.readUsuario(id);
            UsuarioEntity usuario = new UsuarioEntity(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            return usuario;
        }catch (SQLException e){
            e.getMessage();
        }
        return null;
    }

    public static UsuarioEntity buscarUsuarioPorNome(String nome){
        try{
            ResultSet resultSet = QueryTemplate.Usuario.readUsuarioByName(nome);
            UsuarioEntity usuario = new UsuarioEntity(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            return usuario;
        }catch (SQLException e){
            e.getMessage();
        }
        return null;
    }

    public static UsuarioEntity buscarUsuarioPorEmail(String email){
        try{
            ResultSet resultSet = QueryTemplate.Usuario.readUsuarioByEmail(email);
            UsuarioEntity usuario = new UsuarioEntity(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            return usuario;
        }catch (SQLException e){
            e.getMessage();
        }
        return null;
    }
}

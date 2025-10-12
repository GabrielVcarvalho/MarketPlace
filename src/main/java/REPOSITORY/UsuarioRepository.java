package REPOSITORY;

import MODEL.UsuarioEntity;
import DAO.QueryTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioRepository implements UserRepository{
    @Override
    public void salvarUsuario(UsuarioEntity usuario){
        QueryTemplate.Usuario.createUsuario(usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(), usuario.getRole());
    }

    @Override
    public UsuarioEntity lerUsuarioPorId(int id){
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

    @Override
    public UsuarioEntity lerUsuarioPorNome(String nome){
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

    @Override
    public UsuarioEntity lerUsuarioPorEmail(String email){
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

    @Override
    public ArrayList<UsuarioEntity> lerUsuarios() {
        try{
            ResultSet resultSet = QueryTemplate.Usuario.readUsuarios();
            ArrayList<UsuarioEntity> usuarioEntityArrayList = new ArrayList<>();
            while (resultSet.next()){
                UsuarioEntity usuario = new UsuarioEntity(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));
                usuarioEntityArrayList.add(usuario);
            }
            return usuarioEntityArrayList;
        }catch (SQLException e){
            e.getMessage();
        }
        return null;
    }
}

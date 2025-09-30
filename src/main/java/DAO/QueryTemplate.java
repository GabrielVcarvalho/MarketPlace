package DAO;

import MODEL.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTemplate {
    public static class Usuario{
        public void createUsuario(UsuarioEntity usuario){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;

            try{
                pS = connection.prepareStatement("INSERT INTO usuarios (nome, email, senha, role) VALUES (?, ?, ?, ?)");
                pS.setString(1, usuario.getNome().toUpperCase());
                pS.setString(2, usuario.getEmail().toUpperCase());
                pS.setString(3, usuario.getSenha().toUpperCase());
                pS.setString(4, usuario.getRole().toUpperCase());
                pS.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Erro ao criar Usuario");
            } finally {
                ConnectionUtil.closeAll(connection, pS);
            }
        }

    public UsuarioEntity readUsuario(int id){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios WHERE id = ?");
                pS.setInt(1, id);
                rS = pS.executeQuery();

                if(rS.next()){
                    return new UsuarioEntity(rS.getString("nome"),
                            rS.getString("email"),
                            rS.getString("senha"),
                            rS.getString("role"));
                }else return null;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuario");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }

        public UsuarioEntity readUsuario(String nome){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios WHERE nome = ?");
                pS.setString(1, nome.toUpperCase());
                rS = pS.executeQuery();

                if(rS.next()){
                    return new UsuarioEntity(rS.getInt("id"),
                            rS.getString("nome"),
                            rS.getString("email"),
                            rS.getString("senha"),
                            rS.getString("role"));
                }else return null;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuario");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }
    }
}
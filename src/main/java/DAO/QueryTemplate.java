package DAO;

import MODEL.UsuarioEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryTemplate {
    public static class Usuario{
        public static void createUsuario(String nome, String email, String senha, String role){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;

            try{
                pS = connection.prepareStatement("INSERT INTO usuarios (nome, email, senha, role) VALUES (?, ?, ?, ?)");
                pS.setString(1, nome);
                pS.setString(2, email);
                pS.setString(3, senha);
                pS.setString(4, role);
                pS.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Erro ao criar Usuario");
            } finally {
                ConnectionUtil.closeAll(connection, pS);
            }
        }

    public static ResultSet readUsuario(int id){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios WHERE id = ?");
                pS.setInt(1, id);
                rS = pS.executeQuery();

                if(rS.next()){
                    return rS;
                }else return null;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuario");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }

        public static ResultSet readUsuarioByName(String nome){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios WHERE nome = ?");
                pS.setString(1, nome.toUpperCase());
                rS = pS.executeQuery();

                if(rS.next()){
                    return rS;
                }else return null;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuario");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }

        public static ResultSet readUsuarioByEmail(String email){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios WHERE email = ?");
                pS.setString(1, email.toUpperCase());
                rS = pS.executeQuery();

                if(rS.next()){
                    return rS;
                }else return null;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuario");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }

        public static ArrayList<UsuarioEntity> readUsuarios(){
            Connection connection = ConnectionUtil.openConnection();
            PreparedStatement pS = null;
            ResultSet rS = null;
            ArrayList<UsuarioEntity> usuarios = new ArrayList<>();

            try{
                pS = connection.prepareStatement("SELECT id, nome, email, senha, role FROM usuarios");
                rS = pS.executeQuery();

                while(rS.next()){
                    usuarios.add(new UsuarioEntity(rS.getInt("id"),
                            rS.getString("nome"),
                            rS.getString("email"),
                            rS.getString("senha"),
                            rS.getString("role")));
                };
                return usuarios;
            } catch (SQLException e) {
                System.err.println("Erro ao ler Usuarios");
                return null;
            } finally {
                ConnectionUtil.closeAll(connection, pS, rS);
            }
        }
    }
}
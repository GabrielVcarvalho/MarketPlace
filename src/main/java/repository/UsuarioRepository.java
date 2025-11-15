package repository;

import dao.UsuarioDAO;
import model.AnuncioEntity;
import model.UsuarioEntity;
import repository.contracts.UserRepository;

import java.util.ArrayList;

public class UsuarioRepository implements UserRepository {
    private final UsuarioDAO usuarioDAO;

    public UsuarioRepository(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void salvarUsuario(UsuarioEntity usuario){
        usuarioDAO.createUsuario(usuario.getNome(),
                usuario.getEmail(), usuario.getSenha(), usuario.getRole());
    }

    @Override
    public UsuarioEntity lerUsuarioPorId(int id){
        return usuarioDAO.readUsuario(id);
    }

    @Override
    public UsuarioEntity lerUsuarioPorNome(String nome){
            return usuarioDAO.readUsuarioByName(nome);
    }

    @Override
    public UsuarioEntity lerUsuarioPorEmail(String email){
            return usuarioDAO.readUsuarioByEmail(email);
        }

    @Override
    public ArrayList<UsuarioEntity> lerUsuarios() {
        java.util.ArrayList<UsuarioEntity> usuarioEntities = usuarioDAO.readUsuarios();
        return (!usuarioEntities.isEmpty()) ? null : usuarioEntities;
    }
}
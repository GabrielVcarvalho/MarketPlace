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
    public UsuarioEntity lerUsuarioPorId(UsuarioEntity usuario){
            UsuarioEntity usuarioByDTO = usuarioDAO.readUsuario(usuario.getId());
            if(usuarioByDTO != null)
                return usuarioByDTO;
            else
                return null;
    }

    @Override
    public UsuarioEntity lerUsuarioPorNome(String nome){
            model.UsuarioEntity usuario = usuarioDAO.readUsuarioByName(nome);
            if(usuario != null) return usuario;
            else return null;
    }

    @Override
    public UsuarioEntity lerUsuarioPorEmail(String email){
            model.UsuarioEntity usuario = usuarioDAO.readUsuarioByEmail(email);
            if(usuario != null) return usuario;
            else return null;
        }

    @Override
    public ArrayList<UsuarioEntity> lerUsuarios() {
        java.util.ArrayList<UsuarioEntity> usuarioEntities = usuarioDAO.readUsuarios();
        if (!usuarioEntities.isEmpty())
            return usuarioEntities;
        else return null;
    }
}
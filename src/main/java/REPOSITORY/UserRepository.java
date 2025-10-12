package REPOSITORY;

import MODEL.UsuarioEntity;

import java.util.ArrayList;

public interface UserRepository {
    public void salvarUsuario(UsuarioEntity usuarioEntity);

    public UsuarioEntity lerUsuarioPorId(int id);

    public UsuarioEntity lerUsuarioPorNome(String nome);

    public UsuarioEntity lerUsuarioPorEmail(String email);

    public ArrayList<UsuarioEntity> lerUsuarios();
}

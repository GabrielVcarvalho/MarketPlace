package repository.contracts;

import model.UsuarioEntity;

import java.util.ArrayList;

public interface UserRepository {
    void salvarUsuario(UsuarioEntity usuarioEntity);

    UsuarioEntity lerUsuarioPorId(int id);

    UsuarioEntity lerUsuarioPorNome(String nome);

    UsuarioEntity lerUsuarioPorEmail(String email);

    ArrayList<UsuarioEntity> lerUsuarios();
}

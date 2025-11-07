package service.usuario;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.contracts.UserRepository;
import service.usuario.exceptions.EmptyEmail;
import service.usuario.exceptions.EmptyName;
import service.usuario.exceptions.EmptyPassword;

public final class VerificacaoUtils{
    //Private para que não dê para instanciar
    private VerificacaoUtils() {

    }

    static boolean verificarCamposVazios(String nome, String email, String senha){
        if (nome == null || nome.isBlank())
            throw new EmptyName();

        if (email == null || email.isBlank())
            throw new EmptyEmail();

        if (senha == null || senha.isBlank())
            throw new EmptyPassword();

        return true;
    }

     static boolean nameUserAlredyExist(UsuarioDTO usuarioDTO, UsuarioEntity usuarioEntity){
        return usuarioDTO.getNome().equalsIgnoreCase(usuarioEntity.getNome());
    }

     static boolean emailUserAlredyExist(UsuarioDTO usuarioDTO, UsuarioEntity usuarioEntity){
        return usuarioDTO.getEmail().equalsIgnoreCase(usuarioEntity.getEmail());
    }
}
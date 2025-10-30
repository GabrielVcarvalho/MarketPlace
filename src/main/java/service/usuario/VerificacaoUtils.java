package service.usuario;

import repository.contracts.UserRepository;
import service.usuario.exceptions.EmptyEmail;
import service.usuario.exceptions.EmptyName;
import service.usuario.exceptions.EmptyPassword;

public class VerificacaoUtils{
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

     static boolean nameUserAlredyExist(UserRepository userRepository, String nome){
        return userRepository.lerUsuarioPorNome(nome) != null;
    }

     static boolean emailUserAlredyExist(UserRepository userRepository, String email){
        return userRepository.lerUsuarioPorEmail(email) != null;
    }
}
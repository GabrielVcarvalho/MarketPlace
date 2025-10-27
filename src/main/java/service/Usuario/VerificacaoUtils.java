package service.Usuario;

import repository.UserRepository;
import service.Usuario.Exceptions.EmptyEmail;
import service.Usuario.Exceptions.EmptyName;
import service.Usuario.Exceptions.EmptyPassword;

public class VerificacaoUtils{
    public static boolean verificarCamposVazios(String nome, String email, String senha){
        if (nome == null || nome.isBlank()) throw new EmptyName();
        if (email == null || email.isBlank()) throw new EmptyEmail();
        if (senha == null || senha.isBlank()) throw new EmptyPassword();
        return true;
    }

    public static boolean nameUserAlredyExist(UserRepository userRepository, String nome){
        return userRepository.lerUsuarioPorNome(nome) != null;
    }

    public static boolean emailUserAlredyExist(UserRepository userRepository, String email){
        return userRepository.lerUsuarioPorEmail(email) != null;
    }

    public static boolean isValidRole(String role){
        if(role != null){
            return role.equalsIgnoreCase("cliente")
                    || role.equalsIgnoreCase("vendedor");
        }else return false;
    }
}
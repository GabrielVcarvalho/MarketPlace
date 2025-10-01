package SERVICE;

import DAO.QueryTemplate;
import MODEL.UsuarioEntity;
import SERVICE.Exceptions.*;

public class Usuario {
    public static void registrar(String nome, String email, String senha, String role){
        QueryTemplate.Usuario.createUsuario(new UsuarioEntity(nome, email, senha, role));
    }

    private static boolean verificarRegistroUsuario(String nome, String email, String senha, String role){
        if (nome == null || nome.isEmpty()) throw new EmptyName();
        if (email == null || email.isEmpty()) throw new EmptyEmail();
        if (senha == null || senha.isEmpty()) throw new EmptyPassword();
        if (!isValidRole(role)) throw new InvalidRole();
        if (nameUserAlredyExist(nome)) throw new NameUserAlredyExists();
        if (emailUserAlredyExist(email)) throw new EmailUserAlredyUsed();
        return true;
    }

    private static boolean nameUserAlredyExist(String nome){
        return QueryTemplate.Usuario.readUsuarioByName(nome) != null;
    }

    private static boolean emailUserAlredyExist(String email){
        return QueryTemplate.Usuario.readUsuarioByEmail(email) != null;
    }

    private static boolean isValidRole(String role){
        if(role != null){
            String roleToUpper = role.toUpperCase();
            return roleToUpper.equals("CLIENTE") || roleToUpper.equals("VENDEDOR");
        }else return false;
    }
}
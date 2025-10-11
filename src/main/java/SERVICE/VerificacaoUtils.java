package SERVICE;

import DAO.QueryTemplate;
import SERVICE.Exceptions.EmptyEmail;
import SERVICE.Exceptions.EmptyName;
import SERVICE.Exceptions.EmptyPassword;

public class VerificacaoUtils{
    public static boolean verificarCamposVazios(String nome,String email, String senha){
        if (nome == null || nome.isEmpty()) throw new EmptyName();
        if (email == null || email.isEmpty()) throw new EmptyEmail();
        if (senha == null || senha.isEmpty()) throw new EmptyPassword();
        return true;
    }

    public static boolean nameUserAlredyExist(String nome){
        return QueryTemplate.Usuario.readUsuarioByName(nome) != null;
    }

    public static boolean emailUserAlredyExist(String email){
        return QueryTemplate.Usuario.readUsuarioByEmail(email) != null;
    }

    public static boolean isValidRole(String role){
        if(role != null){
            return role.equalsIgnoreCase("cliente")
                    || role.equalsIgnoreCase("vendedor");
        }else return false;
    }
}

package SERVICE;

import DAO.QueryTemplate;
import MODEL.UsuarioEntity;
import SERVICE.Exceptions.*;

public class UsuarioService {

    public static class Registro{
        public static void registrar(UsuarioEntity usuario){
            QueryTemplate.Usuario.createUsuario(usuario);
        }

        public static void verificarRegistroUsuario(UsuarioEntity usuario){
            verificarCamposVazios(usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha());
            if (!isValidRole(usuario.getRole())) throw new InvalidRole();
            if (nameUserAlredyExist(usuario.getNome())) throw new NameUserAlredyExists();
            if (emailUserAlredyExist(usuario.getEmail())) throw new EmailUserAlredyUsed();
        }
    }

    public static class Login{
        public static String login(UsuarioEntity usuario){
            verificarLoginUsuario(usuario);
            return Token.gerarTokenDeUsuario(usuario);
        }

        private static void verificarLoginUsuario(UsuarioEntity usuario){
            verificarCamposVazios(usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha());
            if (!nameUserAlredyExist(usuario.getNome())) throw new NameUserNotExists();
            if (!emailUserAlredyExist(usuario.getEmail())) throw new EmailNotInUse();
            if (!isCorrectPassword(usuario.getSenha(), usuario.getNome())) throw new WrongPassword();
        }

        private static boolean isCorrectPassword(String senha, String email){
            return QueryTemplate.Usuario.readUsuarioByEmail(email).getSenha().equals(senha);
        }
    }

    private static void verificarCamposVazios(String nome,String email, String senha){
        if (nome == null || nome.isEmpty()) throw new EmptyName();
        if (email == null || email.isEmpty()) throw new EmptyEmail();
        if (senha == null || senha.isEmpty()) throw new EmptyPassword();
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
package SERVICE;

import DAO.QueryTemplate;
import SERVICE.Exceptions.EmailNotInUse;
import SERVICE.Exceptions.NameUserNotExists;
import SERVICE.Exceptions.WrongPassword;

public class LoginService {
    private String nome;
    private String email;
    private String senha;
    private String role;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getRole() {
        return role;
    }

    public LoginService(){

    }

    public String login(){
        return new TokenService("Vitor").createToken(nome, email, role);
    }

    public boolean verificarLoginUsuario(){
        VerificacaoUtils.verificarCamposVazios(nome, email, senha);
        if (!VerificacaoUtils.nameUserAlredyExist(nome)) throw new NameUserNotExists();
        if (!VerificacaoUtils.emailUserAlredyExist(email)) throw new EmailNotInUse();
        if (!isCorrectPassword()) throw new WrongPassword();
        return true;
    }

    private boolean isCorrectPassword(){
        return QueryTemplate.Usuario.readUsuarioByName(nome).getSenha().equals(senha);
    }
}
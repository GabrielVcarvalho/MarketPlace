package SERVICE;

import DAO.QueryTemplate;
import MODEL.UsuarioEntity;
import SERVICE.Exceptions.EmailUserAlreadyUsed;
import SERVICE.Exceptions.InvalidRole;
import SERVICE.Exceptions.NameUserAlreadyExists;

public class RegistroService {
    private String nome;
    private String email;
    private String senha;
    private String role;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void registrar(){
        QueryTemplate.Usuario.createUsuario(new UsuarioEntity(nome,
                email, senha, role));
    }

    public void verificarRegistroUsuario(){
        VerificacaoUtils.verificarCamposVazios(nome, email, senha);
        if (!VerificacaoUtils.isValidRole(role)) throw new InvalidRole();
        if (VerificacaoUtils.nameUserAlredyExist(nome)) throw new NameUserAlreadyExists();
        if (VerificacaoUtils.emailUserAlredyExist(email)) throw new EmailUserAlreadyUsed();
    }
}
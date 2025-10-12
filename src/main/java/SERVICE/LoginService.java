package SERVICE;

import DAO.QueryTemplate;
import DTO.UsuarioDTO;
import REPOSITORY.UsuarioRepository;
import SERVICE.Exceptions.EmailNotInUse;
import SERVICE.Exceptions.NameUserNotExists;
import SERVICE.Exceptions.WrongPassword;

public class LoginService {
    public LoginService(){

    }

    public String login(UsuarioDTO usuarioDTO){
        return new TokenService("Vitor").createToken(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());
    }

    public boolean verificarLoginUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());
        if (!VerificacaoUtils.nameUserAlredyExist(usuarioDTO.getNome())) throw new NameUserNotExists();
        if (!VerificacaoUtils.emailUserAlredyExist(usuarioDTO.getEmail())) throw new EmailNotInUse();
        if (!isCorrectPassword(usuarioDTO)) throw new WrongPassword();
        return true;
    }

    private boolean isCorrectPassword(UsuarioDTO usuarioDTO){
        return UsuarioRepository.buscarUsuarioPorNome(usuarioDTO.getNome()).getSenha().equals(usuarioDTO.getSenha());
    }
}
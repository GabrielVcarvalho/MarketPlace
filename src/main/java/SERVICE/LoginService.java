package SERVICE;

import DAO.QueryTemplate;
import DTO.UsuarioDTO;
import REPOSITORY.UserRepository;
import SERVICE.Exceptions.EmailNotInUse;
import SERVICE.Exceptions.NameUserNotExists;
import SERVICE.Exceptions.WrongPassword;

public class LoginService {
    final private UserRepository userRepository;
    private TokenService tokenService;

    public LoginService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String login(UsuarioDTO usuarioDTO){
        return tokenService.createToken(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());
    }

    public boolean verificarLoginUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());
        if (!VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome())) throw new NameUserNotExists();
        if (!VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail())) throw new EmailNotInUse();
        if (!isCorrectPassword(usuarioDTO)) throw new WrongPassword();
        return true;
    }

    private boolean isCorrectPassword(UsuarioDTO usuarioDTO){
        return userRepository.lerUsuarioPorNome(usuarioDTO.getNome()).getSenha().equals(usuarioDTO.getSenha());
    }
}
package SERVICE.Usuario;

import DTO.UsuarioDTO;
import REPOSITORY.UserRepository;
import SERVICE.TokenService;
import SERVICE.Usuario.Exceptions.EmailNotInUse;
import SERVICE.Usuario.Exceptions.NameUserNotExists;
import SERVICE.Usuario.Exceptions.WrongPassword;

public class LoginService {
    final private UserRepository userRepository;
    private final TokenService tokenService;

    public LoginService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String login(UsuarioDTO usuarioDTO){
        return tokenService.criarToken(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());
    }

    public void verificarLoginUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());

        if (!VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome()))
            throw new NameUserNotExists();

        if (!VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail()))
            throw new EmailNotInUse();

        if (!isCorrectPassword(usuarioDTO))
            throw new WrongPassword();
    }

    private boolean isCorrectPassword(UsuarioDTO usuarioDTO){
        return userRepository.lerUsuarioPorNome(usuarioDTO.getNome()).getSenha().equals(usuarioDTO.getSenha());
    }
}
package service.usuario;

import dto.UsuarioDTO;
import repository.UserRepository;
import service.usuario.exceptions.EmailNotInUse;
import service.usuario.exceptions.NameUserNotExists;
import service.usuario.exceptions.WrongPassword;

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
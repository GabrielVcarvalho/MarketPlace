package service.usuario;

import dto.DTOUtils;
import dto.UsuarioDTO;
import repository.contracts.UserRepository;
import service.exceptions.NullDTO;
import service.usuario.contracts.TokenService;
import service.usuario.exceptions.EmailNotInUse;
import service.usuario.exceptions.NameUserNotExists;
import service.usuario.exceptions.WrongPassword;

public class LoginService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginService(
            UserRepository userRepository,
            TokenService tokenService
    ){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String login(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        return tokenService.criarToken(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getRole());
    }

    public void verificarLoginUsuario(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getRole());

        if (!VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome()))
            throw new NameUserNotExists();

        if (!VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail()))
            throw new EmailNotInUse();

        if (!isCorrectPassword(usuarioDTO))
            throw new WrongPassword();
    }

    private boolean isCorrectPassword(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        return userRepository.lerUsuarioPorNome(usuarioDTO.getNome()).getSenha().equals(usuarioDTO.getSenha());
    }
}
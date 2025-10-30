package service.usuario;

import dto.DTOUtils;
import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.contracts.UserRepository;
import service.exceptions.NullDTO;
import service.usuario.contracts.RoleMagenementService;
import service.usuario.exceptions.EmailUserAlreadyUsed;
import service.usuario.exceptions.InvalidRole;
import service.usuario.exceptions.NameUserAlreadyExists;

public class RegistroService {
    private final UserRepository userRepository;
    private final RoleMagenementService roleMagenementService;

    public RegistroService(
            UserRepository userRepository,
            RoleMagenementService roleMagenementService
    ) {
        this.userRepository = userRepository;
        this.roleMagenementService = roleMagenementService;
    }

    public void registrar(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        userRepository.salvarUsuario(new UsuarioEntity(usuarioDTO.getNome(),
                usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getRole()));
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (!roleMagenementService.isValidRole(usuarioDTO.getRole()))
            throw new InvalidRole();

        if (VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome()))
            throw new NameUserAlreadyExists();

        if (VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail()))
            throw new EmailUserAlreadyUsed();
    }
}
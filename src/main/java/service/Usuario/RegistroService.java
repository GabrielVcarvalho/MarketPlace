package service.Usuario;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.UserRepository;
import service.Usuario.Exceptions.EmailUserAlreadyUsed;
import service.Usuario.Exceptions.NameUserAlreadyExists;

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
        userRepository.salvarUsuario(new UsuarioEntity(usuarioDTO.getNome(),
                usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getRole()));
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (!roleMagenementService.isValidRole(usuarioDTO.getRole()))
            throw new InvalidRole();

        if (VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome()))
            throw new NameUserAlreadyExists();

        if (VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail()))
            throw new EmailUserAlreadyUsed();
    }
}
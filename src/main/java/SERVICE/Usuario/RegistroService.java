package SERVICE.Usuario;

import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.UserRepository;
import SERVICE.Usuario.Exceptions.EmailUserAlreadyUsed;
import SERVICE.Usuario.Exceptions.InvalidRole;
import SERVICE.Usuario.Exceptions.NameUserAlreadyExists;

public class RegistroService {
    final private UserRepository userRepository;

    public RegistroService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registrar(UsuarioDTO usuarioDTO){
        userRepository.salvarUsuario(new UsuarioEntity(usuarioDTO.getNome(),
                usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getRole()));
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (!VerificacaoUtils.isValidRole(usuarioDTO.getRole()))
            throw new InvalidRole();

        if (VerificacaoUtils.nameUserAlredyExist(userRepository, usuarioDTO.getNome()))
            throw new NameUserAlreadyExists();

        if (VerificacaoUtils.emailUserAlredyExist(userRepository, usuarioDTO.getEmail()))
            throw new EmailUserAlreadyUsed();
    }
}
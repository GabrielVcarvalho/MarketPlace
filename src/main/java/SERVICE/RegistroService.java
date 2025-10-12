package SERVICE;

import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.UserRepository;
import REPOSITORY.UsuarioRepository;
import SERVICE.Exceptions.EmailUserAlreadyUsed;
import SERVICE.Exceptions.InvalidRole;
import SERVICE.Exceptions.NameUserAlreadyExists;
import org.eclipse.jetty.server.Authentication;

public class RegistroService {
    private UserRepository userRepository;

    public RegistroService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registrar(UsuarioDTO usuarioDTO){
        userRepository.salvarUsuario(new UsuarioEntity(usuarioDTO.getNome(),
                usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getRole()));
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        if (!VerificacaoUtils.isValidRole(usuarioDTO.getRole())) throw new InvalidRole();
        if (VerificacaoUtils.nameUserAlredyExist(usuarioDTO.getNome())) throw new NameUserAlreadyExists();
        if (VerificacaoUtils.emailUserAlredyExist(usuarioDTO.getEmail())) throw new EmailUserAlreadyUsed();
    }
}
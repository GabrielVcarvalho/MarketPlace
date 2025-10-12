package SERVICE;

import DTO.UsuarioDTO;
import MODEL.UsuarioEntity;
import REPOSITORY.UsuarioRepository;
import SERVICE.Exceptions.EmailUserAlreadyUsed;
import SERVICE.Exceptions.InvalidRole;
import SERVICE.Exceptions.NameUserAlreadyExists;

public class RegistroService {
    public RegistroService() {

    }

    public void registrar(UsuarioDTO usuarioDTO){
        UsuarioRepository.salvarUsuario(new UsuarioEntity(usuarioDTO.getNome(),
                usuarioDTO.getEmail(), usuarioDTO.getSenha(), usuarioDTO.getRole()));
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        VerificacaoUtils.verificarCamposVazios(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        if (!VerificacaoUtils.isValidRole(usuarioDTO.getRole())) throw new InvalidRole();
        if (VerificacaoUtils.nameUserAlredyExist(usuarioDTO.getNome())) throw new NameUserAlreadyExists();
        if (VerificacaoUtils.emailUserAlredyExist(usuarioDTO.getEmail())) throw new EmailUserAlreadyUsed();
    }
}
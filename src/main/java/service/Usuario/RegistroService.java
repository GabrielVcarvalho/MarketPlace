package service.Usuario;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.UserRepository;
import service.Usuario.Exceptions.EmailUserAlreadyUsed;
import service.Usuario.Exceptions.InvalidRole;
import service.Usuario.Exceptions.NameUserAlreadyExists;

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
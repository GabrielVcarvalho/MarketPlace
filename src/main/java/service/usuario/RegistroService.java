package service.usuario;

import dto.DTOUtils;
import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.contracts.UserRepository;
import service.exceptions.NullDTO;
import service.mapper.UsuarioMapper;
import service.mapper.contracts.UserMapper;
import service.mapper.exceptions.NullMapperObject;
import service.usuario.contracts.RoleMagenementService;
import service.usuario.exceptions.EmailUserAlreadyUsed;
import service.usuario.exceptions.InvalidRole;
import service.usuario.exceptions.NameUserAlreadyExists;

public class RegistroService {
    private final UserRepository userRepository;
    private final RoleMagenementService roleMagenementService;
    private final UserMapper userMapper;

    public RegistroService(
        UserRepository userRepository,
        RoleMagenementService roleMagenementService,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.roleMagenementService = roleMagenementService;
        this.userMapper = userMapper;
    }

    public void registrar(UsuarioDTO usuarioDTO){
        try {
            userRepository.salvarUsuario(userMapper.convertToEntity(usuarioDTO));
        } catch (NullMapperObject e) {
            throw new NullDTO("O corpo da requisição está vazio");
        }
    }

    public void verificarRegistroUsuario(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("O corpo da requisição está vazio");

        VerificacaoUtils.verificarCamposVazios(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha());

        if (!roleMagenementService.isValidRole(usuarioDTO.getRole()))
            throw new InvalidRole();

        UsuarioEntity entityOutRepository = userRepository.lerUsuarioPorNome(usuarioDTO.getNome());

        if (VerificacaoUtils.nameUserAlredyExist(usuarioDTO, entityOutRepository))
            throw new NameUserAlreadyExists();

        if (VerificacaoUtils.emailUserAlredyExist(usuarioDTO, entityOutRepository))
            throw new EmailUserAlreadyUsed();
    }
}
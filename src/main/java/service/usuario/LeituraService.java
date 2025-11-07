package service.usuario;

import dto.DTOUtils;
import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.UsuarioRepository;
import service.exceptions.NullDTO;
import service.mapper.contracts.UserMapper;
import service.mapper.exceptions.NullMapperObject;
import service.usuario.contracts.RoleMagenementService;
import service.usuario.exceptions.UnauthorizedRole;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LeituraService {
    private final UsuarioRepository usuarioRepository;
    private final RoleMagenementService roleMagenementService;
    private final UserMapper userMapper;

    public LeituraService(
            UsuarioRepository usuarioRepository,
            RoleMagenementService roleMagenementService,
            UserMapper userMapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.roleMagenementService = roleMagenementService;
        this.userMapper = userMapper;
    }

    public ArrayList<UsuarioDTO> lerTodosOsUsuarios(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("Requisição vazia");

        //RoleService faz a verificação de nulo ou vazio da String
        if(!roleMagenementService.isAuthorizedRole(usuarioDTO.getRole(), Role.ADMIN))
            throw new UnauthorizedRole("Usuário não autorizado para a ação");

        ArrayList<UsuarioEntity> entities = usuarioRepository.lerUsuarios();

        return entities.stream()
                .map((usuario -> {
                    try{
                        return userMapper.convertToDTO(usuario);
                    } catch (NullMapperObject e) {
                        //Erro não esperado
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toCollection(ArrayList<UsuarioDTO>::new));
    }
}
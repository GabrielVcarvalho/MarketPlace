package service.usuario;

import dto.DTOUtils;
import dto.UsuarioDTO;
import model.UsuarioEntity;
import repository.UsuarioRepository;
import service.exceptions.NullDTO;
import service.usuario.contracts.RoleMagenementService;
import service.usuario.exceptions.UnauthorizedRole;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LeituraService {
    private final UsuarioRepository usuarioRepository;
    private final RoleMagenementService roleMagenementService;

    public LeituraService(
            UsuarioRepository usuarioRepository,
            RoleMagenementService roleMagenementService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.roleMagenementService = roleMagenementService;
    }

    public ArrayList<UsuarioDTO> lerTodosOsUsuarios(UsuarioDTO usuarioDTO){
        if(DTOUtils.isNull(usuarioDTO))
            throw new NullDTO("Requisição vazia");

        //RoleService faz a verificação de nulo ou vazio da String
        if(!roleMagenementService.isAuthorizedRole(usuarioDTO.getRole(), Role.ADMIN))
            throw new UnauthorizedRole("Usuário não autorizado para a ação");

        ArrayList<UsuarioEntity> entities = usuarioRepository.lerUsuarios();

        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toCollection(ArrayList<UsuarioDTO>::new));
    }

    private UsuarioDTO toDTO(UsuarioEntity usuario){
        return new UsuarioDTO (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}
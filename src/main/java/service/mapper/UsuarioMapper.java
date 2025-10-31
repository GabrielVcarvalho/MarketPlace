package service.mapper;

import dto.UsuarioDTO;
import model.UsuarioEntity;

public class UsuarioMapper{
    public static UsuarioDTO toDTO(UsuarioEntity usuario){
        return new UsuarioDTO (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }

    public static UsuarioEntity toEntity(UsuarioDTO usuario){
        return new UsuarioEntity (
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}

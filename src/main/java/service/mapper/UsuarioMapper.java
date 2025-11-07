package service.mapper;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import service.mapper.contracts.UserMapper;
import service.mapper.exceptions.NullMapperObject;

public class UsuarioMapper implements UserMapper {

    public UsuarioMapper() {

    }

    //Restrinja o uso desses mappers
    public UsuarioDTO convertToDTO(UsuarioEntity usuarioEntity) throws NullMapperObject{
        if(usuarioEntity == null)
            throw new NullMapperObject("Entidade nula, não pôde ser convertida para DTO");

        return new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                usuarioEntity.getRole()
        );
    }

        public UsuarioEntity convertToEntity(UsuarioDTO usuarioDTO) throws NullMapperObject{
        if(usuarioDTO == null)
            throw new NullMapperObject("DTO nulo, não pôde ser convertido para entidade");

        return new UsuarioEntity(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getRole()
        );
    }
}

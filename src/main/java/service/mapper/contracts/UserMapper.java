package service.mapper.contracts;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import service.mapper.exceptions.NullMapperObject;

public interface UserMapper {
    UsuarioDTO convertToDTO(UsuarioEntity usuarioEntity) throws NullMapperObject;

    UsuarioEntity convertToEntity(UsuarioDTO usuarioDTO) throws NullMapperObject;
}

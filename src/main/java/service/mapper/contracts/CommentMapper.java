package service.mapper.contracts;

import dto.ComentarioDTO;
import model.ComentarioEntity;
import service.mapper.exceptions.NullMapperObject;

public interface CommentMapper {
    ComentarioEntity convertToEntity(ComentarioDTO comentarioDTO) throws NullMapperObject;

    ComentarioDTO convertToDTO(ComentarioEntity comentarioEntity) throws NullMapperObject;
}

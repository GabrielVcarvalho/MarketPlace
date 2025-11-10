package service.mapper.contracts;

import dto.ComentarioDTO;
import model.ComentarioEntity;

public interface ComentMapper {
    ComentarioEntity convertToEntity(ComentarioDTO comentarioDTO);

    ComentarioDTO convertToDTO(ComentarioEntity comentarioEntity);
}

package service.mapper;

import dto.ComentarioDTO;
import dto.DTOUtils;
import model.ComentarioEntity;
import service.mapper.contracts.CommentMapper;
import service.mapper.exceptions.NullMapperObject;

public class ComentarioMapper implements CommentMapper {
    @Override
    public ComentarioEntity convertToEntity(ComentarioDTO comentarioDTO) throws NullMapperObject {
        if(DTOUtils.isNull(comentarioDTO))
            throw new NullMapperObject("O corpo da requisição é vazio ou inválido");

        return new ComentarioEntity(
                comentarioDTO.getIdAuthor(),
                comentarioDTO.getIdAnuncio(),
                comentarioDTO.getContent());
    }

    @Override
    public ComentarioDTO convertToDTO(ComentarioEntity comentarioEntity) throws NullMapperObject {
        if(comentarioEntity == null)
            throw new NullMapperObject("O corpo da requisição é vazio ou inválido");

        return new ComentarioDTO(
                comentarioEntity.getIdComment(),
                comentarioEntity.getIdAuthor(),
                comentarioEntity.getAnuncioId(),
                comentarioEntity.getContent());
    }
}
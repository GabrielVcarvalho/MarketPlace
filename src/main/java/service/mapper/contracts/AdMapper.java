package service.mapper.contracts;

import dto.AnuncioDTO;
import model.AnuncioEntity;
import service.mapper.exceptions.NullMapperObject;

public interface AdMapper {
    AnuncioEntity convertToEntity(AnuncioDTO anuncioDTO) throws NullMapperObject;

    AnuncioDTO convertToDTO(AnuncioEntity anuncioEntity) throws NullMapperObject;
}

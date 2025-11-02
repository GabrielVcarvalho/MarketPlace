package service.mapper;

import dto.AnuncioDTO;
import model.AnuncioEntity;
import service.mapper.exceptions.NullMapperObject;

public class AnuncioMapper {

    private AnuncioEntity anuncioEntity;
    private AnuncioDTO anuncioDTO;

    public AnuncioMapper() {

    }

    public AnuncioMapper(AnuncioEntity anuncioEntity) {
        this.anuncioEntity = anuncioEntity;
    }

    public AnuncioMapper(AnuncioDTO anuncioDTO) {
        this.anuncioDTO = anuncioDTO;
    }

    public AnuncioDTO convertToDTO() throws NullMapperObject{
        if (anuncioEntity == null)
            throw new NullMapperObject("Entidade nula, não pôde ser convertida para DTO");

        return new AnuncioDTO(
                anuncioEntity.getId(),
                anuncioEntity.getIdVendedor(),
                anuncioEntity.getTitulo(),
                anuncioEntity.getDescricao(),
                anuncioEntity.getLikes(),
                anuncioEntity.getDeslikes()
        );
    }

    public AnuncioEntity convertToEntity() throws NullMapperObject{
        if (anuncioDTO == null)
            throw new NullMapperObject("DTO nulo, não pôde ser convertido para entidade");

        return new AnuncioEntity(
                anuncioDTO.getId(),
                anuncioDTO.getIdVendedor(),
                anuncioDTO.getTitulo(),
                anuncioDTO.getDescricao(),
                anuncioDTO.getLikes(),
                anuncioDTO.getDeslikes()
        );
    }

    public AnuncioDTO convertToDTO(AnuncioEntity anuncioEntity) throws NullMapperObject{
        if (anuncioEntity == null)
            throw new NullMapperObject("Entidade nula, não pôde ser convertida para DTO");

        return new AnuncioDTO(
                anuncioEntity.getId(),
                anuncioEntity.getIdVendedor(),
                anuncioEntity.getTitulo(),
                anuncioEntity.getDescricao(),
                anuncioEntity.getLikes(),
                anuncioEntity.getDeslikes()
        );
    }

    public AnuncioEntity convertToEntity(AnuncioDTO anuncioDTO) throws NullMapperObject{
        if (anuncioDTO == null)
            throw new NullMapperObject("DTO nulo, não pôde ser convertido para entidade");

        return new AnuncioEntity(
                anuncioDTO.getId(),
                anuncioDTO.getIdVendedor(),
                anuncioDTO.getTitulo(),
                anuncioDTO.getDescricao(),
                anuncioDTO.getLikes(),
                anuncioDTO.getDeslikes()
        );
    }
}
package service.mapper;

import dto.AnuncioDTO;
import model.AnuncioEntity;

public class AnuncioMapper {
    public static AnuncioDTO toDTO(AnuncioEntity anuncio){
        return new AnuncioDTO (
                anuncio.getId(),
                anuncio.getIdVendedor(),
                anuncio.getTitulo(),
                anuncio.getDescricao(),
                anuncio.getLikes(),
                anuncio.getDeslikes()
        );
    }

    public static AnuncioEntity toEntity(AnuncioEntity anuncio){
        return new AnuncioEntity (
                anuncio.getId(),
                anuncio.getIdVendedor(),
                anuncio.getTitulo(),
                anuncio.getDescricao()
        );
    }
}
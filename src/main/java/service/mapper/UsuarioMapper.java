package service.mapper;

import dto.UsuarioDTO;
import model.UsuarioEntity;
import service.mapper.exceptions.NullMapperObject;

public class UsuarioMapper{
    private UsuarioEntity usuarioEntity;
    private UsuarioDTO usuarioDTO;

    public UsuarioMapper() {

    }

    public UsuarioMapper(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public UsuarioMapper(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public UsuarioDTO convertToDTO(){
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

    public UsuarioEntity convertToEntity(){
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

    //Restrinja o uso desses mappers
    public UsuarioDTO convertToDTO(UsuarioEntity usuarioEntity){
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

    public UsuarioEntity convertToEntity(UsuarioDTO usuarioDTO){
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

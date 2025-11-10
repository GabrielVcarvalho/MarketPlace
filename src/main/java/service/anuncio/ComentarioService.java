package service.anuncio;

import dto.ComentarioDTO;
import dto.DTOUtils;
import repository.contracts.AdRepository;
import repository.contracts.UserRepository;
import service.anuncio.exceptions.AnuncioIdNotExists;
import service.anuncio.exceptions.UserIdNotExists;
import service.exceptions.NullDTO;
import service.mapper.contracts.ComentMapper;

public class ComentarioService {
    private final ComentMapper comentMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public ComentarioService(ComentMapper comentMapper, AdRepository adRepository, UserRepository userRepository) {
        this.comentMapper = comentMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public void verificarCriacaoComentario(ComentarioDTO comentarioDTO){
        if(DTOUtils.isNull(comentarioDTO))
            throw new NullDTO("Corpo da requisição vazio ou inválido");

        if(adRepository.lerAnuncioPeloId(comentarioDTO.getIdAnuncio()) == null)
            throw new AnuncioIdNotExists("Id de anuncio não encontrado");

        if(userRepository.lerUsuarioPorId(comentarioDTO.getIdAuthor()) == null)
            throw new UserIdNotExists();
    }

    public void criarComentario(ComentarioDTO comentarioDTO){
        comentMapper.convertToEntity(comentarioDTO);
        //Passar para o repository
    }
}

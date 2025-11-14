package service.anuncio;

import dto.ComentarioDTO;
import dto.DTOUtils;
import model.ComentarioEntity;
import repository.contracts.AdRepository;
import repository.contracts.CommentRepository;
import repository.contracts.UserRepository;
import service.anuncio.exceptions.AnuncioIdNotExists;
import service.anuncio.exceptions.NullContentInComment;
import service.anuncio.exceptions.UserIdNotExists;
import service.exceptions.NullDTO;
import service.exceptions.TriedConvertNullDTOForEntity;
import service.mapper.contracts.CommentMapper;
import service.mapper.exceptions.NullMapperObject;

public class ComentarioService {
    private final CommentMapper commentMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ComentarioService(
            CommentMapper commentMapper,
            AdRepository adRepository,
            UserRepository userRepository,
            CommentRepository commentRepository
    ) {
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void verificarCriacaoComentario(ComentarioDTO comentarioDTO){
        if(DTOUtils.isNull(comentarioDTO))
            throw new NullDTO("Corpo da requisição vazio ou inválido");

        if(comentarioDTO.getContent() == null || comentarioDTO.getContent().isBlank())
            throw new NullContentInComment("O comentário não pode ter conteúdo vazio");

        if(adRepository.lerAnuncioPeloId(comentarioDTO.getIdAnuncio()) == null)
            throw new AnuncioIdNotExists("Id de anuncio não encontrado");

        if(userRepository.lerUsuarioPorId(comentarioDTO.getIdAuthor()) == null)
            throw new UserIdNotExists();
    }

    public void criarComentario(ComentarioDTO comentarioDTO) {
        ComentarioEntity comentarioToEntity;

        try{
            comentarioToEntity = commentMapper.convertToEntity(comentarioDTO);
        } catch (NullMapperObject e) {
            //Esse erro não deveria acontecer NUNCA depois da verificação de null DTO
            //Tratar com error 500
            throw new TriedConvertNullDTOForEntity("Corpo nulo ou inválido não esperado");
        }
        commentRepository.criarComentario(comentarioToEntity);
    }
}
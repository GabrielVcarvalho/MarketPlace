package service.anuncio;

import dto.AvaliacaoDTO;
import dto.DTOUtils;
import repository.contracts.AdRepository;
import repository.contracts.FeedBackRepository;
import repository.contracts.UserRepository;
import service.anuncio.exceptions.AdNotExists;
import service.anuncio.exceptions.InvalidSellerId;
import service.exceptions.NullDTO;

public class FeedBackService {
    private final FeedBackRepository feedBackRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public FeedBackService(
            FeedBackRepository feedBackRepository,
            AdRepository adRepository,
            UserRepository userRepository
    ) {
        this.feedBackRepository = feedBackRepository;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    public void like(AvaliacaoDTO avaliacaoDTO){
        if(DTOUtils.isNull(avaliacaoDTO))
            throw new NullDTO("Requisição vazia");

        verificarIDs(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
        feedBackRepository.adicionarLike(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
    }

    public void removerLike(AvaliacaoDTO avaliacaoDTO){
        if(DTOUtils.isNull(avaliacaoDTO))
            throw new NullDTO("Requisição vazia");

        verificarIDs(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
        feedBackRepository.adicionarLike(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
    }

    public void deslike(AvaliacaoDTO avaliacaoDTO){
        if(DTOUtils.isNull(avaliacaoDTO))
            throw new NullDTO("Requisição vazia");

        verificarIDs(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
        feedBackRepository.adicionarLike(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
    }

    public void removerDeslike(AvaliacaoDTO avaliacaoDTO){
        if(DTOUtils.isNull(avaliacaoDTO))
            throw new NullDTO("Requisição vazia");

        verificarIDs(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
        feedBackRepository.adicionarLike(avaliacaoDTO.getIdAnuncio(), avaliacaoDTO.getIdUsuario());
    }

    private void verificarIDs(int idAnuncio, int idUsuario){
        if(adRepository.lerAnuncioPeloId(idAnuncio) == null)
            throw new AdNotExists("O anuncio informado não existe");

        if(userRepository.lerUsuarioPorId(idUsuario) == null)
            throw new InvalidSellerId();
    }
}
package service.anuncio;

import repository.AdRepository;
import repository.FeedBackRepository;
import repository.UserRepository;
import service.anuncio.exceptions.AdNotExists;
import service.anuncio.exceptions.InvalidSellerId;

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

    public void like(int idAnuncio, int idUsuario){
        verificarIDs(idAnuncio, idUsuario);
        feedBackRepository.adicionarLike(idAnuncio, idUsuario);
    }

    public void removerLike(int idAnuncio, int idUsuario){
        verificarIDs(idAnuncio, idUsuario);
        feedBackRepository.removerLike(idAnuncio, idUsuario);
    }

    public void deslike(int idAnuncio, int idUsuario){
        verificarIDs(idAnuncio, idUsuario);
        feedBackRepository.adicinarDeslike(idAnuncio, idUsuario);
    }

    public void removerDeslike(int idAnuncio, int idUsuario){
        verificarIDs(idAnuncio, idUsuario);
        feedBackRepository.removerDeslike(idAnuncio, idUsuario);
    }

    private void verificarIDs(int idAnuncio, int idUsuario){
        if(adRepository.lerAnuncioPeloId(idAnuncio) == null)
            throw new AdNotExists("O anuncio informado não existe");

        if(userRepository.lerUsuarioPorId(idUsuario) == null)
            throw new InvalidSellerId();
    }
}
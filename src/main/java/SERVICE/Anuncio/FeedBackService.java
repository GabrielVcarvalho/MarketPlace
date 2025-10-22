package SERVICE.Anuncio;

import REPOSITORY.AdRepository;
import REPOSITORY.FeedBackRepository;
import REPOSITORY.UserRepository;
import SERVICE.Anuncio.Exceptions.AdNotExists;
import SERVICE.Anuncio.Exceptions.InvalidSellerId;

public class FeedBackService {
    private final FeedBackRepository feedBackRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    public FeedBackService(FeedBackRepository feedBackRepository, AdRepository adRepository, UserRepository userRepository) {
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

    public void removerDesike(int idAnuncio, int idUsuario){
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
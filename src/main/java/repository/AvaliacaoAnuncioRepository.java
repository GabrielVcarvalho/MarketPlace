package repository;

import dao.DeslikeDAO;
import dao.LikeDAO;

public class AvaliacaoAnuncioRepository implements FeedBackRepository{
    private final LikeDAO likeDAO;
    private final DeslikeDAO deslikeDAO;

    public AvaliacaoAnuncioRepository(LikeDAO likeDAO, DeslikeDAO deslikeDAO) {
        this.likeDAO = likeDAO;
        this.deslikeDAO = deslikeDAO;
    }

    @Override
    public void adicionarLike(int idAnuncio, int idUsuario) {
        likeDAO.createLike(idAnuncio, idUsuario);
    }

    @Override
    public void removerLike(int idAnuncio, int idUsuario) {
        likeDAO.deleteLike(idAnuncio, idUsuario);
    }

    @Override
    public int contarLikes(int idAnuncio) {
        return likeDAO.readLikesOfAd(idAnuncio);
    }

    @Override
    public void adicinarDeslike(int idAnuncio, int idUsuario) {
        deslikeDAO.createDeslike(idAnuncio, idUsuario);
    }

    @Override
    public void removerDeslike(int idAnuncio, int idUsuario) {
        deslikeDAO.deleteDeslike(idAnuncio, idUsuario);
    }

    @Override
    public int contarDeslikes(int idAnuncio) {
        return deslikeDAO.readDeslikesOfAd(idAnuncio);
    }
}
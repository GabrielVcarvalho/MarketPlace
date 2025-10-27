package repository;

import dao.AnuncioDAO;
import dao.DeslikeDAO;
import dao.LikeDAO;
import model.AnuncioEntity;

import java.util.ArrayList;

public class AnuncioRepository implements AdRepository {
    private final AnuncioDAO anuncioDAO;
    private final LikeDAO likeDAO;
    private final DeslikeDAO deslikeDAO;

    public AnuncioRepository(AnuncioDAO anuncioDAO, LikeDAO likeDAO, DeslikeDAO deslikeDAO) {
        this.anuncioDAO = anuncioDAO;
        this.likeDAO = likeDAO;
        this.deslikeDAO = deslikeDAO;
    }

    @Override
    public void criarAnuncio(AnuncioEntity anuncio) {
        anuncioDAO.createAnuncio(anuncio.getIdVendedor(),
                anuncio.getTitulo(), anuncio.getDescricao());
    }

    @Override
    public void removerAnuncio(AnuncioEntity anuncio) {
        anuncioDAO.deleteAnuncio(anuncio.getIdVendedor(), anuncio.getTitulo());
    }

    @Override
    public AnuncioEntity lerAnuncioPeloId(int id) {
        AnuncioEntity anuncio = anuncioDAO.readAnuncioById(id);
        anuncio.setLikes(likeDAO.readLikesOfAd(anuncio.getId()));
        anuncio.setDeslikes(deslikeDAO.readDeslikesOfAd(anuncio.getId()));

        return anuncio;
    }

    @Override
    public AnuncioEntity lerAnuncioPeloNome(String nome) {
        AnuncioEntity anuncio = anuncioDAO.readAnuncioByName(nome);
        anuncio.setLikes(likeDAO.readLikesOfAd(anuncio.getId()));
        anuncio.setDeslikes(deslikeDAO.readDeslikesOfAd(anuncio.getId()));

        return anuncio;
    }

    @Override
    public ArrayList<AnuncioEntity> lerTodosOsAnuncios() {
        return anuncioDAO.readAllFromAnuncios();
    }
}

package repository;

import dao.AnuncioDAO;
import dao.DeslikeDAO;
import dao.LikeDAO;
import dto.DTOUtils;
import model.AnuncioEntity;
import repository.contracts.AdRepository;
import repository.exceptions.NullEntityForRepository;

import java.util.ArrayList;

public class AnuncioRepository implements AdRepository {
    private final AnuncioDAO anuncioDAO;
    private final LikeDAO likeDAO;
    private final DeslikeDAO deslikeDAO;

    public AnuncioRepository(
            AnuncioDAO anuncioDAO,
            LikeDAO likeDAO,
            DeslikeDAO deslikeDAO
    ) {
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
        if(isNullEntity(anuncio))
            throw new NullEntityForRepository();

        anuncioDAO.deleteAnuncio(anuncio.getIdVendedor(), anuncio.getTitulo());
    }

    @Override
    public AnuncioEntity lerAnuncioPeloId(int id) {
        AnuncioEntity anuncioByDAO = anuncioDAO.readAnuncioById(id);

        if(isNullEntity(anuncioByDAO))
            return null;

        anuncioByDAO.setLikes(likeDAO.readLikesOfAd(id));
        anuncioByDAO.setDeslikes(deslikeDAO.readDeslikesOfAd(id));

        return returnAnuncioWithCheck(anuncioByDAO);
    }

    @Override
    public AnuncioEntity lerAnuncioPeloNome(String nome) {
        AnuncioEntity anuncioByDAO = anuncioDAO.readAnuncioByName(nome.toUpperCase());

        if(isNullEntity(anuncioByDAO))
            return null;

        anuncioByDAO.setLikes(likeDAO.readLikesOfAd(anuncioByDAO.getId()));
        anuncioByDAO.setDeslikes(deslikeDAO.readDeslikesOfAd(anuncioByDAO.getId()));

        return returnAnuncioWithCheck(anuncioByDAO);
    }

    @Override
    public ArrayList<AnuncioEntity> lerTodosOsAnuncios() {
        return anuncioDAO.readAllFromAnuncios();
    }

    private boolean isNullEntity(AnuncioEntity anuncio){
        return anuncio == null;
    }

    private AnuncioEntity returnAnuncioWithCheck(AnuncioEntity anuncio){
        return  (isNullEntity(anuncio)) ? null : anuncio;
    }
}